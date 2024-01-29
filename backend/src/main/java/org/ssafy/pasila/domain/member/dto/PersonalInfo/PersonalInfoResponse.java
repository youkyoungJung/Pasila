package org.ssafy.pasila.domain.member.dto.PersonalInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Gender;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoResponse {
    private Long id;
    private String email;
    private String name;
    private String channel;
    private String password;
    private String phone;
    private String address;
    private String addressDetail;
    private Gender gender;
    private LocalDate birth;
    private String bank;
    private String account;
    private String profile;
}
