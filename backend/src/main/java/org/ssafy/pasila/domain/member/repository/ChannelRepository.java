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
        return em.createQuery("SELECT " +
                "() " +
                "FROM " +
                "WHERE ", ChannelLiveDto.class).setParameter("liveId", id).getResultList();
    }

}
