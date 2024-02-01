package org.ssafy.pasila.domain.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**주문이 들어올때 dto*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDto {

    @Schema(description = " 선택한 옵션id")
    Long optionId;

    @Schema(description = "구매자")
    Long memberId;

    @Schema(description = "수량")
    int quantity;

    @Schema(description = "수량인")
    String name;

    @Schema(description = "배송지")
    String address;

    @Schema(description = "가격")
    int price;

}