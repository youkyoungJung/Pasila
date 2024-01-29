package org.ssafy.pasila.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Gender;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInfoRequest {
    @Schema(description = "사용자 이름")
    private String name;
    @Schema(description = "채널명")
    private String channel;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "연락처")
    private String phone;
    @Schema(description = "주소")
    private String address;
    @Schema(description = "상세주소")
    private String addressDetail;
    @Schema(description = "성별")
    private Gender gender;
    @Schema(description = "생년월일")
    private LocalDate birth;
    @Schema(description = "은행")
    private String bank;
    @Schema(description = "계좌번호")
    private String account;
    @Schema(description = "프로필 사진")
    private String profile;
}
