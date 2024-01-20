package org.ssafy.pasila.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "members")
public class Member {
    enum Gender {
        M, F, N
    }

    @Id
    @Column()
    @GeneratedValue
    private Long id;

    @Column(length = 320, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    private String channel;

    @Column(length = 100)
    private String description;

    @Column(length = 100)
    private String password;

    @Column(length = 11, nullable = false)
    private String phone;

    @Column(length = 50)
    private String address;

    @Column(name = "address_detail", length = 50)
    private String addressDetail;

    @Column()
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private Date birth;

    @Column(length = 10)
    private String bank;

    @Column(length = 20)
    private String account;

    @Column(length = 2083)
    private String profile;

    @Column(name = "login_type")
    private LoginType loginType;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Timestamp deletedAt;

    @Column(length = 256)
    private String token;
}