package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Category;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductRequestDto {

    @Schema(description = "제품 정보")
    private Product product;

    @Schema(description = "카테고리")
    private Category category;

    @Schema(description = "판매자 정보")
    private Member member;

    @Schema(description = "제품옵션 정보")
    private List<ProductOption> productOptions;

}