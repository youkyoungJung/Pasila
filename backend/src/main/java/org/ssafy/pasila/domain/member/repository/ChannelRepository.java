package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelDto;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChannelRepository {

    private final EntityManager em;

    public ChannelDto findById(Long id) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.ChannelDto " +
                "(m.channel, m.description, m.profile) " +
                "FROM Member m " +
                "WHERE m.id = :memberId", ChannelDto.class).setParameter("memberId", id).getSingleResult();
    }

    public List<ChannelLiveDto> findLiveById(Long id) {
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
                "AND l.member.id = :memberId", ChannelLiveDto.class)
                .setParameter("memberId", id).getResultList();
    }
}
