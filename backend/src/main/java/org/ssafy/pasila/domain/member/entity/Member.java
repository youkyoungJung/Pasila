package org.ssafy.pasila.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.member.dto.ChannelDTO;
import org.ssafy.pasila.domain.member.dto.request.PersonalInfoRequest;
import org.ssafy.pasila.domain.order.entity.Order;
import org.ssafy.pasila.domain.product.entity.Product;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "member")
@ToString(exclude = {"orders", "liveList", "products"})
public class Member {
    @Id
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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "DATE")
    private LocalDate birth;

    @Column(length = 10)
    private String bank;

    @Column(length = 20)
    private String account;

    @Column(length = 2083)
    private String profile;

    @Column(name = "login_type")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedAt;

    @Column(length = 256)
    private String token;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Live> liveList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();


    /**
     * 프로필 이미지 저장/수정
     */
    public void addProfile(String url){
        this.profile = url;
    }

    /**
     * 마이페이지 - member 수정
     */
    public void updateMember(PersonalInfoRequest personalInfoRequest) {
        this.name = personalInfoRequest.getName();
        this.channel = personalInfoRequest.getChannel();
        this.password = personalInfoRequest.getPassword();
        this.phone = personalInfoRequest.getPhone();
        this.address = personalInfoRequest.getAddress();
        this.addressDetail = personalInfoRequest.getAddressDetail();
        this.gender = personalInfoRequest.getGender();
        this.birth = personalInfoRequest.getBirth();
        this.bank = personalInfoRequest.getBank();
        this.account = personalInfoRequest.getAccount();
        this.profile = personalInfoRequest.getProfile();
    }

    // 채널 설명 수정
    public void updateChannel(String description) {
        this.description = description;
    }

}