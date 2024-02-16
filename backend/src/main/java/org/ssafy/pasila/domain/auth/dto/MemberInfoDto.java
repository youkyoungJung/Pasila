package org.ssafy.pasila.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.pasila.domain.member.entity.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {

    @Schema(description = "회원 아이디")
    private Long id;

    @Schema(description = "회원 이메일")
    private  String email;

    @Schema(description = "회원 비밀번호")
    private  String password;

    @Schema(description = "회원 권한")
    private String role;

}
