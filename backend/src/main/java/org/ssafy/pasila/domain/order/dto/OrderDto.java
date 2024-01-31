package org.ssafy.pasila.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import java.time.LocalDateTime;


//1. 주문 생성할 때
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private Integer orderCnt;
    private String name;
    private String address;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private Member member;
    private ProductOption productOption;

}
