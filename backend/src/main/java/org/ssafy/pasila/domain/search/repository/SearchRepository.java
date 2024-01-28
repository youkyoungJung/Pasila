package org.ssafy.pasila.domain.search.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponse;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponse;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class SearchRepository {
    private final EntityManager em;

    public List<SearchLiveResponse> findAllByNameForLive(String keyword) {
        String likeParam = "%"+keyword+"%";
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchLiveResponse" +
                                "(l.id, l.title, m.name, m.channel, p.name) " +
                                "FROM Live l  left Join l.product p  left  Join l.member m " +
                                "where l.title Like :keyword " +
                                "or p.name Like :keyword " +
                                "or m.channel Like :keyword " +
                                "and l.isActive = true "
                        ,  SearchLiveResponse.class)
                .setParameter("keyword", likeParam)
                .setParameter("keyword", likeParam)
                .setParameter("keyword", likeParam)
                .getResultList();
    }

    public List<SearchShortpingResponse> findAllByNameForShortping(String keyword){
        String likeParam = "%"+keyword+"%";
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponse" +
                                "(s.id, s.title) " +
                                "FROM Shortping s  left Join s.product p  left  Join p.member m " +
                                "where s.title Like :keyword " +
                                "or p.name Like :keyword " +
                                "or m.channel Like :keyword " +
                                "and s.isActive = true "
                        ,  SearchShortpingResponse.class)
                .setParameter("keyword", likeParam)
                .setParameter("keyword", likeParam)
                .setParameter("keyword", likeParam)
//                .setParameter("true", 1)
                .getResultList();

    }



}
