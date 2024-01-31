package org.ssafy.pasila.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**주문이 들어올때 dto*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDto {
    Long optionId; // 선택한 옵션ID
    Long memberId; // 구매자
    int quantity; // 수량
    String name; // 수령인
    String address; // 배송지
    int price;

}
