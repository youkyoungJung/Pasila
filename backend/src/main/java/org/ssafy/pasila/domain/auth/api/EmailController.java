package org.ssafy.pasila.domain.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.auth.dto.EmailDto;
import org.ssafy.pasila.domain.auth.service.EmailService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "EmailAuth", description = "EmailAuth API")
public class EmailController {

    private final EmailService emailService;
    
    @Operation(summary = "Send Authentication Number", description = "해당 이메일로 인증번호 발송")
    @PostMapping("/email")
    public ApiCommonResponse<?> sendEmail(@RequestBody Map<String ,String> email){
            try {
                emailService.sendEmail(email.get("email"));
                return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
            } catch (Exception e) {
                log.error(e.getMessage());
                return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value() , false);
            }

    }

    @Operation(summary = "Check Authentication Number", description = "이메일 코드 확인")
    @PutMapping("/email")
    public ApiCommonResponse<?> checkEmailCode(@RequestBody EmailDto emailDto){
        try {
            if(emailService.checkEmailCode(emailDto)){
                return ApiCommonResponse.successResponse(HttpStatus.OK.value(), true);
            }else{
                return ApiCommonResponse.successResponse(HttpStatus.BAD_REQUEST.value(), false);
            }
        }catch (Exception e) {
            return ApiCommonResponse.successResponse(HttpStatus.NOT_FOUND.value(), false);
        }
    }

}
