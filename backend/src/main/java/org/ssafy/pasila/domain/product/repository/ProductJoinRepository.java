package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.dto.ProductResponseDto;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJoinRepository {

    private final EntityManager em;

    public List<ProductResponseDto> findAllWithCategory() {

        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.product.dto.ProductResponseDto" +
                                "(p.id, m.id, p.name, p.description, p.createdAt, p.thumbnail, c.id) " +
                                "FROM Product p Join p.category c Join p.member m " ,  ProductResponseDto.class)
                .getResultList();

    }

    public ProductResponseDto findById(String productId){

        return em.createQuery("Select new org.ssafy.pasila.domain.product.dto.ProductResponseDto" +
                        "(p.id, m.id, p.name, p.description, p.createdAt, p.thumbnail, c.id) " +
                        "FROM Product p Join p.category c Join p.member m " +
                        "where p.id = :productId " , ProductResponseDto.class)
                .setParameter("productId", productId).getSingleResult();

    }

}