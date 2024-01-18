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
    @Column(name="members_id")
    @GeneratedValue
    Long id;
    String email;
    String name;
    String channel;

    @Column(name="get_channel_desc")
    String getChannelDesc;

    String password;
    String phone;
    String address;

    @Column(name = "address_detail")
    String addressDetail;
    boolean gender;
    Date birth;
    String bank;
    String profile;

    @Column(name = "create_at")
    Date createAt;

    String token;

}
