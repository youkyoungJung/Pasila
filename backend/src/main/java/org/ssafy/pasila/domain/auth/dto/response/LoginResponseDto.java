package org.ssafy.pasila.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.pasila.domain.member.entity.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "이메일 주소")
    private String email;

    @Schema(description = "유저 이름")
    private String name;

    @Schema(description = "채널 명")
    private String channel;

    @Schema(description = "설명")
    private String description;

    @Schema(description = "핸드폰 번호")
    private String phone;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "상세 주소")
    private String addressDetail;

    @Schema(description = "성별")
    private Gender gender;

    @Schema(description = "생일")
    private LocalDate birth;

    @Schema(description = "은행")
    private String bank;

    @Schema(description = "계좌 번호")
    private String account;

    @Schema(description = "프로필")
    private String profile;

    @Schema(description = "토큰")
    private String token;

}
