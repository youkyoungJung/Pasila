package org.ssafy.pasila.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductOption {
    private int id;
    private int productId;
    private String name;
    private int stock;
    private int price;
    private int discountPrice;
}
