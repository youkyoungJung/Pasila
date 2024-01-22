package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.product.entity.Product;

import java.time.LocalDateTime;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shortping {
    @Id
    private String id;

    @Column(length = 30)
    private String title;

    @Column(name = "like_cnt")
    private Integer likeCnt;

    @Column(name = "video_url")
    private String videoUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}
