package org.ssafy.pasila.domain.auth.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.auth.dto.request.LoginRequestDto;
import org.ssafy.pasila.domain.auth.service.LoginService;
import org.ssafy.pasila.domain.member.entity.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Login", description = "Login API")
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "Login", description = "로그인")
    @PostMapping("login")
    public ApiCommonResponse<?> Login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        Member member = this.loginService.login(request);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(),member);
    }
}