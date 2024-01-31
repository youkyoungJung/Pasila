package org.ssafy.pasila.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.auth.service.SendMsgService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SendMsgController {

    private  final SendMsgService sendMsgService;

    @PostMapping("/sms")
    public ResponseEntity<?> sendMsg(@RequestBody String phoneNum){

        try {
            sendMsgService.sendSMS(phoneNum);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
