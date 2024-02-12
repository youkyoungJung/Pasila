package org.ssafy.pasila.domain.sell.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.ssafy.pasila.domain.order.entity.Status;

@Getter
@Builder
public class OrderManagementDetailDto {

    @Schema(description = "주문 id")
    private Long id;

    @Schema(description = "구매자 이름")
    private String buyerName;

    @Schema(description = "배송지")
    private String address;

    //구매 옵션
    @Schema(description = "주문 옵션 이름")
    private String productOptionName;

    @Schema(description = "주문 수량")
    private int orderCnt;

    @Schema(description = "배송 상태")
    private Status status;

    @Schema(description = "결재 금액")
    private int price;

}