package org.ssafy.pasila.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;
    private Long sellerId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnail;
    private Long categoryId;

    public ProductResponse(String id, String name){
        this.id = id;
        this.name = name;
    }

    public ProductResponse(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductResponse(String id, Long sellerId, String name, String description,
                           LocalDateTime createdAt, String thumbnail, Long categoryId){
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
    }

}
