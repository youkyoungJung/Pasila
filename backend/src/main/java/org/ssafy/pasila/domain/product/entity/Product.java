package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.ssafy.pasila.domain.member.entity.Member;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Product {
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(255)")
    private String id;

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
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<>();

    //==연관 메서드 ==//
    public void addProductOption(ProductOption productOption){
        productOptions.add(productOption);
        productOption.setProduct(this);
    }

    //== 생성 메서드 ==//
    public static Product initializeProduct(Product product, Category category, List<ProductOption> productOptions){
        Product create = new Product();
        create.setName(product.getName());
        create.setDescription(product.getDescription());
        create.setCreatedAt(LocalDateTime.now());
        create.setCategory(category);
        for(ProductOption productOption : productOptions){
            create.addProductOption(productOption);
        }
        create.setThumbnail(product.getThumbnail());
        create.setActive(true);
        return create;

    }
    public void updateProduct(Product product, Category category) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.category = category;
    }
}
