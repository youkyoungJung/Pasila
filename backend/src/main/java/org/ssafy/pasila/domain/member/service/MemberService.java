package org.ssafy.pasila.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.auth.dto.request.LoginRequestDto;
import org.ssafy.pasila.domain.auth.service.EncryptService;
import org.ssafy.pasila.domain.live.entity.LiveStatus;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.dto.ChannelLiveStatusDto;
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
import org.ssafy.pasila.domain.member.dto.PersonalInfoDto;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.ChannelRepository;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final ChannelRepository channelRepository;

    private final S3Uploader s3Uploader;

    private final PasswordEncoder encoder;

    private final EncryptService encryptService;

    private final LiveRepository liveRepository;

    /**
     * 사용자 정보 수정 메서드
     */
    @Transactional
    public Long updateMember(Long id, PersonalInfoDto request, MultipartFile newImageFile) throws IOException {
        Member result = getMemberById(id);

        request.setAccount(encryptService.encryptAccount(request.getAccount()));
        if (request.getPassword().isEmpty()) {
            result.updateMember(request);
        } else {
            request.setPassword(encoder.encode(request.getPassword()));
            result.updateMemberWithPw(request);
        }
        if(!newImageFile.isEmpty()) {
            handleImage(result, newImageFile);
        }
        return result.getId();
    }

    /**
     * 채널정보 수정 메서드
     */
    @Transactional
    public Long updateChannel(Long id, String description) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));
        member.updateChannel(description);
        return member.getId();
    }


    /**
     * 채널별 라이브 조회 메서드
     */
    public List<ChannelShortpingDto> getChannelShortpingById(Long id) {
        return channelRepository.findShortpingById(id);
    }

    /**
     * 비밀번호 확인 메서드
     */
    public Boolean checkPW(Long id, String pw) {
        return encoder.matches(pw, getMemberById(id).getPassword());
    }

    /**
     * 멤버 조회 메서드
     */
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 멤버 정보가 없습니다."));
    }

    /**
     * 채널별 라이브 조회 메서드
     */
//    private List<ChannelLiveDto> getChannelLiveById(Long id) {
//        return channelRepository.findLiveById(id);
//    }

    /**
     * 채널별 라이브 & 상태 조회 메서드
     */
//    public List<ChannelLiveStatusDto> getChannelLiveStatusById(Long id) {
//        List<ChannelLiveStatusDto> results = new ArrayList<>();
//        List<ChannelLiveDto> lives = getChannelLiveById(id);
//
//        for(ChannelLiveDto live: lives){
//            boolean isReserve = false;
//            boolean isProgress = false;
//            boolean isEnd = false;
//
//            if(live.getLiveOffAt() != null){
//                isEnd = true;
//            } else if (live.getLiveOnAt() != null) {
//                isProgress = true;
//            }else {
//                isEnd = true;
//            }
//
//            ChannelLiveStatusDto result =ChannelLiveStatusDto.builder()
//                    .live(live)
//                    .isReserve(isReserve)
//                    .isProgress(isProgress)
//                    .isEnd(isEnd)
//                    .build();
//
//            results.add(result);
//        }
//
//        return results;
//    }

    /**
     * 이미지가 있을 경우 S3Upload에 접근, upload
     * 반환된 url을 바탕으로 memberProfile에 저장
     */
    private void handleImage(Member member, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            log.info("member:{}", member);
            String storedFileName = s3Uploader.upload(member.getId().toString(), image, "images");
            member.addProfile(storedFileName);
        }
    }

    /**
     * 새로운 이미지의 수정이 필요할 경우
     * 이미지를 삭제하고 새로운 이미지를 등록시킴
     */
    private void handleNewImage(Member member, MultipartFile newImageFile) throws IOException {
        String originImageUrl = member.getProfile();
        log.info("originImageUrl: {}", originImageUrl);
        if (!newImageFile.isEmpty()) {
            deleteImageIfExists(originImageUrl);
            handleImage(member, newImageFile);
        }
    }

    // 이미지가 존재할 경우 삭제하는 경우

    /**
     * Profile은 하나이므로 만약 사진이 수정될 경우
     * S3에서의 기존 사진이 삭제되어야함.
     */
    private void deleteImageIfExists(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = extractFileName(imageUrl);
            s3Uploader.deleteImage(fileName);
            log.info("success: deleteImage 수행");
        }
    }

    //imageFile 이름을 추출하는 메서드

    /**
     * S3에 저장된 이미지 url은 https://amazon.~~~com/이미지파일명
     * 으로 저장되어있기 때문에 .com/ 을 바탕으로
     * substring 함수를 이용하여 url을 가공시켜 이미지파일명을 추출함.
     */
    private String extractFileName(String imageUrl) {
        String splitStr = ".com/";
        return imageUrl.substring(imageUrl.lastIndexOf(splitStr) + splitStr.length());
    }

    public boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkChannel(String channel) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByChannel(channel));
        if (member.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public void join(Member member, MultipartFile profileFile) throws IOException {
        member.encodePassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);

        if (profileFile != null && !profileFile.isEmpty()) {
            String url = s3Uploader.upload(member.getId().toString(), profileFile, "member");
            member.addProfile(url);
        }

    }

    @Transactional
    public void updatePassword(LoginRequestDto dto){
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));
        member.updatePassword(encoder.encode(dto.getPassword()));

    }

    /**
     * 채널별 라이브 조회 메서드
     */
    private List<ChannelLiveDto> getLiveInMyChannel(Long id) {

        //채널별 라이브 조회
        return liveRepository.findByMember_Id(id)
                .stream()
                .map(ChannelLiveDto::new)
                .toList();

    }

    public List<ChannelLiveStatusDto> getChannelLiveStatusById(Long id) {
        List<ChannelLiveDto> lives = getLiveInMyChannel(id);
        List<ChannelLiveStatusDto> channelLiveStatusDtos = new ArrayList<>();

        for (ChannelLiveDto live : lives) {
            LocalDateTime currentDateTime = LocalDateTime.now();

            ChannelLiveStatusDto channelLiveStatusDto = ChannelLiveStatusDto.builder()
                    .live(live)
                    .status(calculateStatus(live, currentDateTime))
                    .build();

            channelLiveStatusDtos.add(channelLiveStatusDto);
        }

        return channelLiveStatusDtos;
    }

    private LiveStatus calculateStatus(ChannelLiveDto live, LocalDateTime currentDateTime) {

        if (live.getLiveOnAt() != null && live.getLiveOnAt().isBefore(currentDateTime)
                && (live.getLiveOffAt() == null || live.getLiveOffAt().isAfter(currentDateTime))) {
            return LiveStatus.IN_PROGRESS;
        } else if (live.getLiveOffAt() != null && live.getLiveOffAt().isBefore(currentDateTime)) {
            return LiveStatus.ENDED;
        } else if (live.getLiveOnAt() != null && live.getLiveOnAt().isAfter(currentDateTime)) {
            return LiveStatus.RESERVED;
        }
        return null; // 예외 처리 등을 위해 기본값 반환
    }


}
