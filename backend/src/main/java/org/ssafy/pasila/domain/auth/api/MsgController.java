package org.ssafy.pasila.domain.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.auth.dto.MsgDto;
import org.ssafy.pasila.domain.auth.service.MsgService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MsgController {

    private  final MsgService MsgService;

    @Operation(summary = "Send Authentication Number", description = "해당 번호로 인증번호 발송")
    @PostMapping("/sms")
    public ApiCommonResponse<?> sendMsg(@RequestBody Map<String , String> phoneNum){
        try {
            MsgService.sendSMS(phoneNum.get("phoneNum"));
            return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
        }catch (Exception e){
            return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
        }
    }

    @Operation(summary = "Check Authentication Number", description = "문자 인증번호 확인")
    @PutMapping("/sms")
    public ApiCommonResponse<?> checkMsgCode(@RequestBody MsgDto msgDto){
        try {
            if(MsgService.checkMsgCode(msgDto)){
                return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
            }else{
                return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
            }
        }catch (Exception e) {
            return ApiCommonResponse.successResponse(HttpStatus.NOT_FOUND.value(), false);
        }
    }

}
