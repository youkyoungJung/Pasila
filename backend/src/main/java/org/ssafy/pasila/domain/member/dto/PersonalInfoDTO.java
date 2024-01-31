package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Gender;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDTO {
    @Schema(description = "사용자 메일주소")
    private String email;
    @Schema(description = "사용자 이름")
    private String name;
    @Schema(description = "사용자 채널명")
    private String channel;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "사용자 연락처")
    private String phone;
    @Schema(description = "사용자 주소")
    private String address;
    @Schema(description = "사용자 상세주소")
    private String addressDetail;
    @Schema(description = "사용자 성별")
    private Gender gender;
    @Schema(description = "사용자 생년월일")
    private LocalDate birth;
    @Schema(description = "사용자 은행")
    private String bank;
    @Schema(description = "사용자 계좌번호")
    private String account;
    @Schema(description = "사용자 프로필 사진")
    private String profile;

    public PersonalInfoDTO(String email, String name, String channel, String phone, String address,
                           String addressDetail, Gender gender, LocalDate birth, String bank,
                           String account, String profile) {
        this.email = email;
        this.name = name;
        this.channel = channel;
        this.phone = phone;
        this.address = address;
        this.addressDetail = addressDetail;
        this.gender = gender;
        this.birth = birth;
        this.bank = bank;
        this.account = account;
        this.profile = profile;
    }
}
