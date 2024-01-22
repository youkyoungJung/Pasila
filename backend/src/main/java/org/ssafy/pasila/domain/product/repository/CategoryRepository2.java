package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;
import org.ssafy.pasila.domain.product.entity.Category;
import org.ssafy.pasila.domain.product.entity.Product;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository2 {
    private final EntityManager em;

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }


}
