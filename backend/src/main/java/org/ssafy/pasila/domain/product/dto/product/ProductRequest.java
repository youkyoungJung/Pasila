package org.ssafy.pasila.domain.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.ssafy.pasila.domain.product.entity.Product;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private Product product;
    private Long categoryId;
}
