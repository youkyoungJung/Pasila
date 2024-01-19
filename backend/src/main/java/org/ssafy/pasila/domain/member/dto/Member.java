package org.ssafy.pasila.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    private int id;// 사용자id: int
    private String email;// 이메일: VARCHAR(320) / NN
    private String name;// 이름: VARCHAR(10)
    private String channel;// 채널명: VARCHAR(10)
    private String description;// 채널 설명: VARCHAR(100)
    private String password;// 비밀번호: VARCHAR(100)
    private String phone;// 전화번호: VARCHAR(11)
    private String address;// 주소: VARCHAR(50)
    private String address_detail;// 상세주소: VARCHAR(50)
    private int gender;// 성별: TINYINT
    private String birth;// 생년월일: VARCHAR(8)
    private String bank;// 은행: VARCHAR(10)
    private String account;// 계좌번호: VARCHAR(20)
    private String profile;// 프로필사진: VARCHAR(2083)
    private Date created_at;// 가입일: datetime
    private String login_type;// 로그인타입: enum("google", "kakao", "naver", "email")
    private String token;// 토큰: VARCHAR(256)
}
