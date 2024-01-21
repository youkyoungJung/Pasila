package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "product_option")
public class ProductOption {
    @Id
    private Long id;

    @Column(length = 20)
    private String name;

    private Long stock;

    private Long price;

    @Column(name = "discount_price")
    private Long discountPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
