package org.ssafy.pasila.domain.order.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.member.entity.Member;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    private Long id;

    // private Member member;
    // private Product product;
    // private ProductOption productOption;

    private Long orderCnt;

    private String name;

    private String address;

    private String status;

    private Long price;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

}
