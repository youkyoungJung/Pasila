package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSellResponseDto {

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

    @Schema(description = "제품 옵션")
    private List<ProductOptionDto> options;

    @Schema(description = "은행")
    private String bank;

    @Schema(description = "계좌")
    private String account;

    @Schema(description = "대본")
    private String script;

    @Builder
    public ProductSellResponseDto(String id, Long sellerId, String name, String description, LocalDateTime createdAt,
                                  String thumbnail, Long categoryId, List<ProductOptionDto> options, String bank,
                                  String account, String script){

        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
        this.options = options;
        this.bank = bank;
        this.account = account;
        this.script = script;

    }

    public void setAccount(String account) {
        this.account = account;
    }
}
