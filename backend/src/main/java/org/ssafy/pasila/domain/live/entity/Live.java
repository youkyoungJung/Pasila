package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.GenericGenerator;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

import java.time.LocalDateTime;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Live {
    @Id
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Column(columnDefinition = "BINARY(16)")
    private String id;

    @Column(length = 30)
    private String title;

    @Column(name = "live_on_at")
    private LocalDateTime liveOnAt;

    @Column(name = "live_off_at")
    private LocalDateTime liveOffAt;

    @Lob
    private String script; // 필요하지 않으면 굳이 읽지 않는 것이 좋음

    @Column(name = "full_video_url")
    private String fullVideoUrl;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "is_on")
    private boolean isOn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private LocalDateTime isActive;

    @OneToOne
    private Shortping shortping;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "livelog")
    private List<Livelog> livelogs;

}
