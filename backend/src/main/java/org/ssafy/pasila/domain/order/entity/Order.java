package org.ssafy.pasila.domain.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.entity.ProductOption;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "order_cnt")
    private Long orderCnt;

    @Column(length = 10)
    private String name;

    @Column(length = 50)
    private String address;

    @Column(length = 10)
    private String status;

    private Long price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

}
