package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelDto;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
<<<<<<< backend/src/main/java/org/ssafy/pasila/domain/member/repository/ChannelRepository.java
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;
=======
>>>>>>> backend/src/main/java/org/ssafy/pasila/domain/member/repository/ChannelRepository.java

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

    public List<ChannelShortpingDto> findShortpingById(Long id) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.ChannelShortpingDto" +
                        "(s.id, s.title, s.createdAt, " +
                        "m.id, m.channel, m.profile, " +
                        "p.id, p.thumbnail, " +
                        "po.price, po.discountPrice) " +
                        "FROM Shortping s " +
                        "JOIN s.product p " +
                        "JOIN p.member m " +
                        "JOIN p.productOptions po " +
                        "WHERE po.id = " +
                        "(SELECT MIN(po2.id) FROM ProductOption po2 WHERE po2.product.id = p.id " +
                        "AND po2.price = " +
                        "(SELECT MIN(po3.price) FROM ProductOption po3 WHERE po3.product.id = p.id) " +
                        "AND po2.discountPrice = " +
                        "(SELECT MIN(po4.discountPrice) FROM ProductOption po4 WHERE po4.product.id = p.id)) " +
                        "AND m.id = :memberId",
                        ChannelShortpingDto.class)
                .setParameter("memberId", id).getResultList();
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
