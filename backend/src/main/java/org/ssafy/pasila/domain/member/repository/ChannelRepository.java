package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelDTO;

@Repository
@RequiredArgsConstructor
public class ChannelRepository {

    private final EntityManager em;

    public ChannelDTO findById(Long id) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.ChannelDTO " +
                "(m.channel, m.description, m.profile) " +
                "FROM Member m " +
                "WHERE m.id = :memberId", ChannelDTO.class).setParameter("memberId", id).getSingleResult();
    }
}
