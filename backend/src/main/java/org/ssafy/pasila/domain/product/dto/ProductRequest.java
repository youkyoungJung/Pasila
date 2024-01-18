package org.ssafy.pasila.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.ssafy.pasila.domain.product.entity.Product;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private Product product;
    private Long largeCategoryId;
    private Long middleCategoryId;
    private Long detailCategoryId;
}
