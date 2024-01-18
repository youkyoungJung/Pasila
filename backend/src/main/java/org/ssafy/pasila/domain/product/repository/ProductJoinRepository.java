package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.entity.Product;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJoinRepository {
    private final EntityManager em;

    public List<Product> findAllWithLargeCategory() {
        return em.createQuery(
                        "select p from Product p join p.largeCategory",
                        Product.class)
                .getResultList();
    }

}
