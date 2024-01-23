package org.ssafy.pasila.domain.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    // 내 정보 조회
    @GetMapping("/personal-info/{memberId}")
    public Optional<Member> getMember(@PathVariable("memberId") Long memberId) {
        return memberRepository.findMemberByMemberId(memberId);
    }

    // 내 정보 수정

    // 내 채널 조회

    // 내 채널 수정 - 채널명

    // 내 채널 수정 - 채널설명

}
