package org.ssafy.pasila.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class ProductOption {
    @Id
    private Long id;

    // private Product product

    private String name;

    private Long stock;

    private Long price;

    private Long discountPrice;
}
