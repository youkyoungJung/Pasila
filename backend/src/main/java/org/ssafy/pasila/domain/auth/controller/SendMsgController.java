package org.ssafy.pasila.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.auth.service.SendMsgService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SendMsgController {

    private  final SendMsgService sendMsgService;

    @Operation(summary = "Send Authentication Number", description = "해당 번호로 인증번호 발송")
    @PostMapping("/sms")
    public ApiCommonResponse<?> sendMsg(@RequestBody String phoneNum){
        try {
            sendMsgService.sendSMS(phoneNum);
            return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
        }catch (Exception e){
            return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
        }
    }

}
