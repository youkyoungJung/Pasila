package org.ssafy.pasila.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.beans.Transient;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final S3Uploader uploader;

    @Override
    public boolean checkEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            return false; //이미 존재하는 경우
        }else {
            return true; // 생성가능
        }
    }

    @Override
    public boolean checkChannel(String channel) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByChannel(channel));
        if(member.isPresent()){
            return false; // 이미 존재하는 경우 
        }else {
            return true;  // 생성가능
        }
    }

    @Transactional
    @Override
    public void join(Member member, MultipartFile profileFile) throws IOException {
        // profileFile 을 S3에서 받아온 url 주소를 member profile

        String url = uploader.upload(profileFile , "member");
        member.addProfile(url);
        memberRepository.save(member);
    }

    @Override
    public Member login(String email, String password) {

        Optional<Member> findMember = memberRepository.findByEmail(email);
        findMember.orElseThrow(()->new IllegalStateException("해당 이메일이 존재하지 않습니다."));

        if( findMember.get().getPassword().equals(password)){
            throw new IllegalStateException("이메일과 비밀번호가 일치하지 않습니다.");
        }
        return findMember.get();

    }


}
