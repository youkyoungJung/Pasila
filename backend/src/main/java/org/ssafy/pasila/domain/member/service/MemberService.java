package org.ssafy.pasila.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.dto.request.PersonalInfoRequest;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public void updateMember(Long id, PersonalInfoRequest personalInfoRequest, MultipartFile newImageFile) throws IOException {
        Member result = getMemberById(id);
        result.updateMember(personalInfoRequest.getName(), personalInfoRequest.getChannel(), personalInfoRequest.getPassword(),
                personalInfoRequest.getPhone(), personalInfoRequest.getAddress(), personalInfoRequest.getAddressDetail(),
                personalInfoRequest.getGender(), personalInfoRequest.getBirth(), personalInfoRequest.getBank(),
                personalInfoRequest.getAccount(), personalInfoRequest.getProfile());
        handleImage(result, newImageFile);
        log.info("update member: {}", result);
    }

    /** 멤버 조회 메서드 */
    private Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 아이디에 대한 멤버 정보가 없습니다."));
    }

    /**
     * 이미지가 있을 경우 S3Upload에 접근, upload
     * 반환된 url을 바탕으로 memberProfile에 저장
     * */
    private void handleImage(Member member, MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            log.info("member:{}", member);
            String storedFileName = s3Uploader.upload(member.getId().toString(), image, "images");
            member.editProfileUrl(storedFileName);
        }
    }

    /**
     * 새로운 이미지의 수정이 필요할 경우
     * 이미지를 삭제하고 새로운 이미지를 등록시킴
     * */
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
     * */
    private void deleteImageIfExists(String imageUrl) {
        if (!imageUrl.isEmpty()) {
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
     * */
    private String extractFileName(String imageUrl) {
        String splitStr = ".com/";
        return imageUrl.substring(imageUrl.lastIndexOf(splitStr) + splitStr.length());
    }
}
