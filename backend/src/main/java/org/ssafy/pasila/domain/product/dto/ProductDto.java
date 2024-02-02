package org.ssafy.pasila.domain.product.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Category;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Schema(description = "상태 코드")
    private int statusCode;

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

    @Schema(description = "제품 판매자")
    private Member member;

    @Schema(description = "제품 카테고리")
    private Category category;

}