package org.ssafy.pasila.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
import org.ssafy.pasila.domain.member.dto.PersonalInfoDto;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.ChannelRepository;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;
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

    /**
     * 사용자 정보 수정 메서드
     */
    @Transactional
    //TODO 비밀번호 암호화, 계좌번호 암호화
    public Long updateMember(Long id, PersonalInfoDto request, MultipartFile newImageFile) throws IOException {
        Member result = getMemberById(id);
        /* 계좌번호 암호화 */
//        request.setAccount(암호화 메서드);
        if (request.getPassword().isEmpty()) {
            result.updateMember(request);
        } else {
            /* 비밀번호 암호화 */
//            request.setPassword(암호화 메서드);
            result.updateMemberWithPw(request);
        }
        handleImage(result, newImageFile);
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
        return getMemberById(id).getPassword().equals(pw);
//        return getMemberById(id).getPassword().equals(암호화메서드(pw));
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
    public List<ChannelLiveDto> getChannelLiveById(Long id) {
        return channelRepository.findLiveById(id);
    }

    /**
     * 이미지가 있을 경우 S3Upload에 접근, upload
     * 반환된 url을 바탕으로 memberProfile에 저장
     */
    private void handleImage(Member member, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
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
            return false; //이미 존재하는 경우
        } else {
            return true; // 생성가능
        }
    }

    public boolean checkChannel(String channel) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByChannel(channel));
        if (member.isPresent()) {
            return false; // 이미 존재하는 경우
        } else {
            return true;  // 생성가능
        }
    }

    @Transactional
    public void join(Member member, MultipartFile profileFile) throws IOException {
        // profileFile 을 S3에서 받아온 url 주소를 member profile
        if (!profileFile.isEmpty()) {
            String url = s3Uploader.upload(member.getId().toString(), profileFile, "member");
            member.addProfile(url);
        }
        memberRepository.save(member);
    }

    public Member login(String email, String password) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        findMember.orElseThrow(() -> new IllegalStateException("The email address does not exit."));

        if (findMember.get().getPassword().equals(password)) {
            throw new IllegalStateException("Invalid email or password.");
        }
        return findMember.get();
    }

}
