package org.ssafy.pasila.domain.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.product.entity.ProductOption;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDto {

    @Schema(description = "옵션 id")
    private Long id;

    @Schema(description = "옵션 명")
    private String name;

    @Schema(description = "옵션 재고")
    private int stock;

    @Schema(description = "옵션 가격")
    private int price;

    @Schema(description = "옵션 할인가")
    private int discountPrice;

    @Schema(description = "제품 id")
    private String productId;

    public ProductOptionDto(ProductOption productOption) {
        this.id = productOption.getId();
        this.name = productOption.getName();
        this.stock = productOption.getStock();
        this.price = productOption.getPrice();
        this.discountPrice = productOption.getDiscountPrice();
        this.productId = productOption.getProduct().getId();
    }

}
