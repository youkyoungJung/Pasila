package org.ssafy.pasila.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;

import java.beans.Transient;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean checkEmail(String email) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findByEmail(email));
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
    public void join(Member member, MultipartFile profileFile){
        // profileFile 을 S3에서 받아온 url 주소를 member profile


        String url = null;
        member.addProfile(url);
        memberRepository.save(member);
    }

}
