package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    @Schema(description = "제품 id")
    private String id;

    @Schema(description = "제품 판매자 id")
    private Long sellerId;

    @Schema(description = "제품 이름")
    private String name;

    @Schema(description = "제품 설명")
    private String description;

    @Schema(description = "생성일자")
    private LocalDateTime createdAt;

    @Schema(description = "수정일자")
    private LocalDateTime updatedAt;

    @Schema(description = "제품 썸네일")
    private String thumbnail;

    @Schema(description = "제품 카테고리 id")
    private Long categoryId;

    public ProductResponseDto(String id, String name){
        this.id = id;
        this.name = name;
    }

    public ProductResponseDto(String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductResponseDto(String id, Long sellerId, String name, String description,
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