package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.ssafy.pasila.domain.member.entity.Member;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.sqids.Sqids;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"category", "productOptions"})
@Entity
public class Product {

    @Id
    @Column(columnDefinition = "VARCHAR(12)")
    private String id;

    @PrePersist
    public void createUniqId() {
        Sqids sqids = Sqids.builder()
                .minLength(12)
                .build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampAsLong = timestamp.getTime();
        String newId = sqids.encode(List.of(timestampAsLong));

        this.id = newId;
    }

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 10000)
    private String description;

    @Column(length = 2083)
    private String thumbnail;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductOption> productOptions = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Shortping shortping;

    //== 생성 메서드 ==//
    /**상품 저장 시 카테고리와 seller 정보를 저장할 수 있는 메서드 */
    public void addProductWithCategoryWithMember(Category category, Member member){

        this.category = category;
        this.member = member;
    }

    /** product 관련 없데이트 , 카테고리 변경 */
    public void updateProduct(Product product, Category category) {

        this.name = product.getName();
        this.description = product.getDescription();
        this.category = category;
    }

    /** 썸네일을 저장하거나 변경할 때 사용하는 메서드*/
    public void addThumbnailUrl(String url){

        this.thumbnail = url;

    }

    public void setActive(boolean active){

        this.isActive = active;

    }

    public void setShortping(Shortping shortping) {

        this.shortping = shortping;

    }

}