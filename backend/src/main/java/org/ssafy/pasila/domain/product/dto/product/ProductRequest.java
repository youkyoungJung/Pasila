package org.ssafy.pasila.domain.product.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Category;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.entity.ProductOption;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {

    private Product product;
    private Category category;
    private Member member;
    private List<ProductOption> productOptions;

}
