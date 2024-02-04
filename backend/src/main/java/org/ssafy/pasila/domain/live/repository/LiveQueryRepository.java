package org.ssafy.pasila.domain.live.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;

@Repository
@RequiredArgsConstructor
public class LiveQueryRepository {
    private final EntityManager em;

    public Live findByProductId(String id) {

        return em.createQuery("select l from Live l" +
                " where l.product.id =: productId", Live.class)
                .setParameter("productId", id)
                .getSingleResult();

    }
}
