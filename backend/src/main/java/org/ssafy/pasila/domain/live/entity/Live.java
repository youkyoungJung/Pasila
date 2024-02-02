package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.sqids.Sqids;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Live {
    @Id
    @Column(columnDefinition = "VARCHAR(10)")
    private String id;

    @PrePersist
    public void createUniqId() {

        Sqids sqids = Sqids.builder()
                .minLength(8)
                .build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampAsLong = timestamp.getTime();
        String newId = sqids.encode(Arrays.asList(timestampAsLong, 1L));

        this.id = newId;

    }

    @Column(length = 30)
    private String title;

    @Column(name = "live_scheduled_at")
    private LocalDateTime liveScheduledAt;

    @Column(name = "live_on_at")
    private LocalDateTime liveOnAt;

    @Column(name = "live_off_at")
    private LocalDateTime liveOffAt;

    @Lob
    private String script;

    @Column(name = "full_video_url", length = 2083)
    private String fullVideoUrl;

    @Column(name = "like_cnt")
    private Integer likeCnt;

    @Column(name = "is_on")
    private boolean isOn;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    private List<Livelog> livelogs = new ArrayList<>();

}
