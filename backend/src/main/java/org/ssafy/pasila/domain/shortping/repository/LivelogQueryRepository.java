package org.ssafy.pasila.domain.shortping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LivelogQueryRepository {

    private final EntityManager em;

    // 영상의 하이라이트 리스트 가져오기
    public List<Livelog> findAllByLive(String liveId) {

        return em.createQuery(
                "select lg from Livelog lg" +
                        " where lg.live.id =: liveId", Livelog.class)
                .setParameter("liveId", liveId)
                .getResultList();

    }

    public void deleteAllByLiveId(String liveId) {
        em.createQuery("delete from Livelog lg" +
                " where lg.live.id =: liveId")
                .setParameter("liveId", liveId)
                .executeUpdate();
    }

}
