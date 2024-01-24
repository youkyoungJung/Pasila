package org.ssafy.pasila.domain.member.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;

import java.io.IOException;

public interface MemberService {

    public boolean checkEmail(String email);

    public boolean checkChannel(String channel);

    public void join(Member member, MultipartFile profileFile) throws IOException;

    public Member login(String email, String password);

}
