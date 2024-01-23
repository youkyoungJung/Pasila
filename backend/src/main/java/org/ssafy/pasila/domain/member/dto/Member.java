package org.ssafy.pasila.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Gender;
import org.ssafy.pasila.domain.member.entity.LoginType;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private Long id;// 사용자id: int
    private String email;// 이메일: VARCHAR(320) / NN
    private String name;// 이름: VARCHAR(10)
    private String channel;// 채널명: VARCHAR(10)
    private String description;// 채널 설명: VARCHAR(100)
    private String password;// 비밀번호: VARCHAR(100)
    private String phone;// 전화번호: VARCHAR(11)
    private String address;// 주소: VARCHAR(50)
    private String addressDetail;// 상세주소: VARCHAR(50)
    private Gender gender;// 성별: TINYINT
    private Date birth;// 생년월일: VARCHAR(8)
    private String bank;// 은행: VARCHAR(10)
    private String account;// 계좌번호: VARCHAR(20)
    private String profile;// 프로필사진: VARCHAR(2083)
    private LocalDateTime createdAt;// 가입일: datetime
    private LocalDateTime deletedAt;// 탈퇴일
    private LoginType loginType;// 로그인타입: enum("google", "kakao", "naver", "email")
    private String token;// 토큰: VARCHAR(256)
}
