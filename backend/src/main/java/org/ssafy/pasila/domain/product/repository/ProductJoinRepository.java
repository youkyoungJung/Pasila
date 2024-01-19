package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.product.ProductResponse;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJoinRepository {
    private final EntityManager em;

    public List<ProductResponse> findAllWithCategory() {
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.product.dto.product.ProductResponse" +
                                "(p.id, p.sellerId, p.name, p.description, p.createdAt, l.id, m.id, d.id) " +
                                "FROM Product p " +
                                "join p.largeCategory l " +
                                "join p.middleCategory m " +
                                "join p.detailCategory d ",  ProductResponse.class)
                .getResultList();
    }

    public ProductResponse findById(Long productId){
        return em.createQuery("Select new org.ssafy.pasila.domain.product.dto.product.ProductResponse" +
                        "(p.id, p.sellerId, p.name, p.description, p.createdAt, l.id, m.id, d.id) " +
                        "FROM Product p " +
                        "join p.largeCategory l " +
                        "join p.middleCategory m " +
                        "join p.detailCategory d " +
                        "where p.id = :productId " , ProductResponse.class)
                .setParameter("productId", productId).getSingleResult();
    }


}
