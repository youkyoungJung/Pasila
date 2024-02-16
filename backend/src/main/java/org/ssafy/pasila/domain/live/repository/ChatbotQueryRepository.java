package org.ssafy.pasila.domain.live.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChatbotQueryRepository {
    private final EntityManager em;

    public void deleteAllByLiveId(String liveId) {
        em.createQuery(
                "delete from Chatbot c " +
                        "where c.live.id = :liveId")
                .setParameter("liveId", liveId)
                .executeUpdate();
    }
}
