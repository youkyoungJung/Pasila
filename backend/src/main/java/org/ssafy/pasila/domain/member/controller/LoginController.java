package org.ssafy.pasila.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.member.service.MemberService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final MemberService memberService;

//    public ResponseEntity<?> login(String email, String password) {
//
//        Member member = memberService.login(email, password);
//
//
//        return findMember.get();
//    }


}
