package org.ssafy.pasila.domain.shortping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponse;

@Repository
@RequiredArgsConstructor
public class ShortpingQueryRepository {

    private final EntityManager em;

    public ShortpingResponse findWithProductMember(String id) {

        return em.createQuery(
                "select new org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponse" +
                        "(s.id, s.title, s.videoUrl, s.likeCnt, s.createdAt, p.id, p.name, p.thumbnail, p.description, c.id, m.id, m.channel)" +
                        " from Shortping s" +
                        " join s.product p" +
                        " join p.category c" +
                        " join p.member m" +
                        " where s.id=:shortpingId", ShortpingResponse.class)
                .setParameter("shortpingId", id)
                .getSingleResult();

    }
}
