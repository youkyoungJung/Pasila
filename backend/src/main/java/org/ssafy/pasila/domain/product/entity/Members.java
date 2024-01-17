package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Members {
    @Id
    @GeneratedValue
    Long id;
    String email;
    String name;
    String channel;
    String getChannelDesc;
    String password;
    String phone;
    String address;
    String address_detail;
    boolean gender;
    Date birth;
    String bank;
    String profile;
    Date createAt;
    String token;

}
