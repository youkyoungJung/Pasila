package org.ssafy.pasila.domain.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.service.MemberService;
import org.ssafy.pasila.domain.member.service.SendMsgService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {


    private final MemberService memberService;

    private  final SendMsgService sendMsgService;

    //유효한 이메일인지 확인하는 부분 추가
    @GetMapping("/member/email")
    public ResponseEntity<?> checkEmail(@RequestParam String email){

        if(memberService.checkEmail(email)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }

    }


    @GetMapping("/member/channel/{channel}")
    public ResponseEntity<?> checkChannel(@PathVariable("channel") String channel){
        if(memberService.checkChannel(channel)) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/member/join")
    public ResponseEntity<?> join(@RequestPart("file") MultipartFile file , @RequestPart("member") Member member) throws IOException {
        memberService.join(member, file);
        return new ResponseEntity<Boolean>(true , HttpStatus.OK);
    }

    @GetMapping("/member/sms")
    public void sendMsg(@RequestParam String phoneNum){
        sendMsgService.sendSMS(phoneNum);
    }


}
