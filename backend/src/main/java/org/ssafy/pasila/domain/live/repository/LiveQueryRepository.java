package org.ssafy.pasila.domain.live.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;

import java.time.LocalDate;
import java.util.List;

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

    public List<ChannelLiveDto> findScheduledLiveByDate(LocalDate date) {
        return em.createQuery("""
        SELECT new org.ssafy.pasila.domain.member.dto.ChannelLiveDto
               (l.id, l.title, l.liveScheduledAt, l.liveOnAt, l.liveOffAt, m.id, m.channel, m.profile, p.id, p.thumbnail, po.discountPrice, po.price, s.id)
          FROM Live l
               JOIN l.member m
               JOIN l.product p
               JOIN p.productOptions po
               JOIN p.shortping s
         WHERE po.id = (SELECT MIN(po2.id)
                          FROM ProductOption po2
                         WHERE po2.product = p
                                AND po2.discountPrice = (SELECT MIN(po3.discountPrice)
                                     FROM ProductOption po3
                                    WHERE po3.product = p))
               AND l.isActive = true
               AND DATE(l.liveScheduledAt) = :scheduledAt""", ChannelLiveDto.class)
                .setParameter("scheduledAt", date).getResultList();
    }
}
