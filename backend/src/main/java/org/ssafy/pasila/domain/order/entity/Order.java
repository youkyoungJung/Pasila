package org.ssafy.pasila.domain.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "order_cnt")
    private Integer orderCnt;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String address;

    private Integer price;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    private Status status; // 상태 [CANCLE, ORDER, DEPOSIT, READY, START, COMP]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id")
    @JsonIgnore
    private ProductOption productOption;

    //== 생성 메서드 ==//
    public static Order createOrder(OrderFormDto orderFormDto, Member member, ProductOption productOption){

        Order saved = Order.builder()
                .orderCnt(orderFormDto.getQuantity())
                .name(orderFormDto.getName())
                .address(orderFormDto.getAddress())
                .price(orderFormDto.getPrice()) //수량 * 할인가
                .status(Status.ORDER)
                .member(member)
                .productOption(productOption)
                .build();
        //재고 수량 감소
        productOption.removeStock(orderFormDto.getQuantity());
        return saved;

    }

    //== 주문 취소 ==//
    public void cancel(){

        if(this.status != Status.ORDER){
            throw new RestApiException(ErrorCode.NOT_CANCELLATION);
        }
        this.status = Status.CANCEL;
        this.productOption.addStock(this.orderCnt);

    }

    public void changeStatus(Status status){

        this.status = status;

    }

}