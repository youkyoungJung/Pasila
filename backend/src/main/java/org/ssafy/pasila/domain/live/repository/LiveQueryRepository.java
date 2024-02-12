package org.ssafy.pasila.domain.live.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;

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

    public List<ChannelLiveDto> findAllByCategory(Long categoryId, String sort) {
        String orderQuery = "";
        if (!sort.isEmpty()){
            orderQuery = " ORDER BY l.";
            switch(sort) {
                case "latest": // 최신순
                    orderQuery += "liveOnAt";
                    break;
                case "like":
                    orderQuery += "likeCnt";
            }
            orderQuery += " DESC";
        }
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.ChannelLiveDto" +
                        "(l.id, l.title, l.liveScheduledAt, " +
                        "m.id, m.channel, m.profile, " +
                        "p.id, p.thumbnail, " +
                        "po.discountPrice, po.price, " +
                        "s.id) " +
                        "FROM Live l " +
                        "JOIN l.member m " +
                        "JOIN l.product p " +
                        "JOIN p.productOptions po " +
                        "JOIN p.shortping s " +
                        "WHERE po.id = (SELECT MIN(po2.id) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                        "AND s.id = (SELECT MIN(s2.id) FROM Shortping s2 WHERE s2.product.id = p.id) " +
                        "AND l.isActive = true " +
                        "AND l.liveOnAt IS NOT NULL " +
                        "AND p.category.id = :categoryId" + orderQuery, ChannelLiveDto.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

}
