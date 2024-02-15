package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.sqids.Sqids;
import org.ssafy.pasila.domain.product.entity.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shortping")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE shortping SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
public class Shortping {

    @Id
    @Column(columnDefinition = "VARCHAR(15)")
    private String id;

    @PrePersist
    public void createUniqId() {

        Sqids sqids = Sqids.builder()
                .minLength(10)
                .build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampAsLong = timestamp.getTime();
        String newId = sqids.encode(List.of(timestampAsLong, 2L));

        this.id = newId;

    }

    @Column(length = 50, nullable = false)
    private String title;

    @Column(name = "like_cnt")
    @ColumnDefault("0")
    private Integer likeCnt;

    @Column(name = "video_url", length = 2083)
    private String videoUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active", columnDefinition = "TINYINT")
    @ColumnDefault("1")
    private boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
        product.setShortping(this);
    }

    public Shortping(String title, String videoUrl, Product product) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.likeCnt = 0;
        this.isActive = true;
        setProduct(product);
    }

}
