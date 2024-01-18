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

//             return em.createQuery(
//                     "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
//                     " from Order o" +
//                     " join o.member m" +
//                     " join o.delivery d", OrderSimpleQueryDto.class)

    public List<ProductRequest> findAllWithLargeCategory() {
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.product.dto.ProductRequest(p.id, l.id, m.id) " +
                                "FROM Product p " +
                                "join p.largeCategory l " +
                                "join p.middleCategory m" ,  ProductRequest.class)
                .getResultList();
    }

}
