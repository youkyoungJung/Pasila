package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Product {
    @Id @GeneratedValue
    private String id;

    @Column(length = 30)
    private String name;

    @Column(length = 10000)
    private String description;

    @Column(length = 2083)
    private String thumbnail;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne()
    private LargeCategory largeCategory;

    @ManyToOne()
    private MiddleCategory middleCategory;

    @ManyToOne()
    private DetailCategory detailCategory;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOption;
}
