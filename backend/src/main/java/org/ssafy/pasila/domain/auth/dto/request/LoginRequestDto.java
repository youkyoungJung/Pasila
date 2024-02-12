package org.ssafy.pasila.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;

    @NotBlank(message = "패스워드 입력은 필수입니다.")
    private String password;

}
