package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelDto;

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

}
