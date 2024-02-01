package org.ssafy.pasila.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.order.entity.Order;
import org.ssafy.pasila.domain.order.entity.Status;
import java.time.LocalDateTime;

@Data
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
    private Status status;
    private Member member;
    private Long productOptionId;
    private String productOptionName;
    private String productName;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderCnt = order.getOrderCnt();
        this.name = order.getName();
        this.address = order.getAddress();
        this.price = order.getPrice();
        this.createdAt = order.getCreatedAt();
        this.deletedAt = order.getDeletedAt();
        this.status = order.getStatus();
        this.member = order.getMember();
        this.productOptionId = order.getProductOption().getId();
        this.productOptionName = order.getProductOption().getName();
        this.productName = order.getProductOption().getProduct().getName();
    }
}
