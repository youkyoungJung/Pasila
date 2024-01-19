package org.ssafy.pasila.domain.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductResponse {

    private Long id;
    private Long sellerId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnail;

    private Long largeCategoryId;
    private Long middleCategoryId;
    private Long detailCategoryId;

    public ProductResponse(Long id, Long sellerId, String name, String description,
                           LocalDateTime createdAt, Long largeCategoryId, Long middleCategoryId, Long detailCategoryId){
        this.id = id;
        this.sellerId = sellerId;
        this.description = description;
        this.createdAt = createdAt;
        this.largeCategoryId = largeCategoryId;
        this.middleCategoryId = middleCategoryId;
        this.detailCategoryId = detailCategoryId;
    }

}
