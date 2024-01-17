package org.ssafy.pasila.domain.product.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.product.entity.Product;

import java.util.List;

/**
 * product repository
 * jpa 결과 반환(DAO)
 */
@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    //상품 등록, 수정
    public void save(Product product){
            em.merge(product);
    }

    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

    //전체 상품 select
    public List<Product> findAll() {
        return em.createQuery("select i from Product i", Product.class)
                .getResultList();
    }
    //상품 검색 (%like% 채널, 라이브 제목 검색)


}
