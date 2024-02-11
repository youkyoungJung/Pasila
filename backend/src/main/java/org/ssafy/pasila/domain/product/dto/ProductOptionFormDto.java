package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOptionFormDto {

    @Schema(description = "옵션 id")
    private Long id;

    @Schema(description = "수량")
    private int quantity;

    @Schema(description = "가격")
    private int price;

}
