package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.response.ChannelResponse;

@Repository
@RequiredArgsConstructor
public class ChannelRepository {

    private final EntityManager em;

    public ChannelResponse findById(Long id) {
        return em.find(ChannelResponse.class, id);
    }
}
