package org.ssafy.pasila.domain.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {


    private final MemberService memberService;


    //유효한 이메일인지 확인하는 부분 추가
    @GetMapping("/email")
    public ResponseEntity<?> checkEmail(@RequestParam String email){

        if(memberService.checkEmail(email)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/channel")
    public ResponseEntity<?> checkChannel(@RequestParam String channel){
        if(memberService.checkChannel(channel)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value = "/join" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> join(@RequestPart(value = "member") Member member ,
                                  @RequestPart(value = "profileFile" , required = false) MultipartFile profileFile ){
        try {
            memberService.join(member, profileFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }catch (Exception e){
            String errorMessage = "An error occurred: " + e.getMessage();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }




}
