package org.ssafy.pasila.domain.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Product Entity 파일
 * JPA 테이블 생성
 */
@Entity
@Table(name = "product")
@Getter
@Setter

public class Product {

    @Id
    @GeneratedValue
    @Column(name="product_id")
    private Long id;

    @Column(name="seller_id")
    private Long sellerId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="large_category_id")
    @JsonIgnore
    private LargeCategory largeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="middle_category_id")
    @JsonIgnore
    private MiddleCategory middleCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_category_id")
    @JsonIgnore
    private DetailCategory detailCategory;

    private String name;

    private String description;

    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String thumbnail;

    //==관련 메서드 ==//


}