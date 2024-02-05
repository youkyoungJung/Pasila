package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @Schema(description = "제품 id")
    private String id;

    @Schema(description = "제품 이름")
    private String name;

    @Schema(description = "제품 설명")
    private String description;

    @Schema(description = "제품 썸네일")
    private String thumbnail;

    @Schema(description = "제품 생성일자")
    private LocalDateTime createdAt;

    @Schema(description = "제품 수정일자")
    private LocalDateTime updatedAt;

    @Schema(description = "제품 활성화 여부")
    private boolean isActive;

    @Schema(description = "판매자 id")
    private Long memberId;

    @Schema(description = "제품 카테고리 id")
    private Long categoryId;

    @Schema(description = "옵션 리스트")
    private List<ProductOptionDto> options;

}
