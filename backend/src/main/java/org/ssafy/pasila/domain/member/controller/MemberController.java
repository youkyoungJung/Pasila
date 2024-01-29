package org.ssafy.pasila.domain.member.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.member.dto.response.PersonalInfoResponse;
import org.ssafy.pasila.domain.member.repository.PersonalInfoRepository;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@Tag(name = "Member", description = "Member API")
public class MemberController {

    private final PersonalInfoRepository personalInfoRepository;

    // 마이페이지(personal-info)

    // 회원 정보 조회 by id
    @Operation(summary = "get member by id", description = "마이페이지 - id로 회원 정보 조회")
    @GetMapping("/personal-info/{id}")
//    public ResponseEntity<?> getMember(@PathVariable("id") Long id) {
//        return response.handleSuccess(200, personalInfoRepository.findById(id));
//    }
    public Optional<PersonalInfoResponse> getMember(@PathVariable("id") Long id){
        return Optional.ofNullable(personalInfoRepository.findById(id));
    }


}
