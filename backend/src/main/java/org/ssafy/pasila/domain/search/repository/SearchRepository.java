package org.ssafy.pasila.domain.search.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.search.dto.SearchResponse;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class SearchRepository {
    private final EntityManager em;

    public List<SearchResponse> findAllByName(String keyword) {
        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchResponse" +
                                "(l.id, l.title, m.name, m.channel, p.name) " +
                                "FROM Live l  left Join l.product p  left  Join l.member m " +
                                "where l.title Like :keyword " +
                                "or p.name Like :keyword " +
                                "or m.channel Like :keyword "
                        ,  SearchResponse.class)
                .setParameter("keyword", "%"+ keyword+ "%")
                .setParameter("keyword", "%"+ keyword+ "%")
                .setParameter("keyword", "%"+ keyword+ "%")
                .getResultList();
    }



}
