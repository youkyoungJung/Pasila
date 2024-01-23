package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.sqids.Sqids;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.product.entity.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shortping {
    @Id
    @Column(columnDefinition = "VARCHAR(8)")
    private String id;

    @PrePersist
    public void createUniqId() {
        Sqids sqids = Sqids.builder()
                .minLength(8)
                .build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampAsLong = timestamp.getTime();
        String newId = sqids.encode(List.of(timestampAsLong, 2L));

        this.id = newId;
    }

    @Column(length = 30)
    private String title;

    @Column(name = "like_cnt")
    private Integer likeCnt;

    @Column(name = "video_url", length = 2083)
    private String videoUrl;

    @Column(length = 2083)
    private String thumbnail;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
