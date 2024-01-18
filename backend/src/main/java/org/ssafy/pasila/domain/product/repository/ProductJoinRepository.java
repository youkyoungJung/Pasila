package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.ProductRequest;
import org.ssafy.pasila.domain.product.entity.Product;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJoinRepository {
    private final EntityManager em;

    public List<ProductRequest> findAllWithCategory() {
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.product.dto.ProductRequest" +
                                "(p.id, p.sellerId, p.name, p.description, " +
                                "l.id, m.id) " +
                                "FROM Product p " +
                                "join p.largeCategory l " +
                                "join p.middleCategory m",  ProductRequest.class)
                .getResultList();
    }


}
