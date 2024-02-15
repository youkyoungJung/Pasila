package org.ssafy.pasila.domain.sell.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderManagementDto {

    @Schema(description = "제품 id")
    private String id;

    @Schema(description = "제품 url")
    private String thumbnail;

    @Schema(description = "제품 이름")
    private String name;

    @Schema(description = "제품 가격")
    private int price;

    @Schema(description = "제품 할인가")
    private int discount;

    @Schema(description = "방송 일")
    private LocalDateTime liveOnAt;

    @Schema(description = "재고")
    private List<ProductOptionDto> options;

}