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

    public List<SearchLiveResponse> findAllByNameForLive(String keyword, String sort) {
        String orderByClause = getOrderByClause(sort);
        String likeParam = createLikeParam(keyword);

        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchLiveResponse" +
                                "(l.id, l.title, m.name, m.channel, p.name) " +
                                "FROM Live l LEFT JOIN l.product p LEFT JOIN l.member m " +
                                "WHERE (l.title LIKE :keyword " +
                                "OR p.name LIKE :keyword " +
                                "OR m.channel LIKE :keyword) " +
                                "AND l.isActive = true " +
                                orderByClause
                        , SearchLiveResponse.class)
                .setParameter("keyword", likeParam)
                .getResultList();
    }

    public List<SearchShortpingResponse> findAllByNameForShortping(String keyword, String sort) {
        String orderByClause = getOrderByClause(sort);
        String likeParam = createLikeParam(keyword);

        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponse" +
                                "(s.id, s.title) " +
                                "FROM Shortping s LEFT JOIN s.product p LEFT JOIN p.member m " +
                                "WHERE s.title LIKE :keyword " +
                                "OR p.name LIKE :keyword " +
                                "OR m.channel LIKE :keyword " +
                                "AND s.isActive = true " +
                                orderByClause
                        , SearchShortpingResponse.class)
                .setParameter("keyword", likeParam)
                .getResultList();
    }

    /** 정렬 조건 (인기순/최신순) */
    private String getOrderByClause(String sort) {
        if ("created_at".equals(sort)) {
            return "ORDER BY l.createdAt DESC"; // 생성일자(createdAt) 내림차순으로 정렬
        } else {
            return "ORDER BY l.likeCnt DESC"; // 인기도(popularity) 내림차순으로 정렬
        }
    }

    /** keyword 로 검색 */
    private String createLikeParam(String keyword) {
        return "%" + keyword + "%";
    }
}
