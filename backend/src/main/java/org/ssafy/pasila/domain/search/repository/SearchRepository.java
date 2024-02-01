package org.ssafy.pasila.domain.search.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final EntityManager em;

    public List<SearchLiveResponseDto> findAllByNameForLive(String keyword, String sort) {
        String orderByClause = getOrderByClause(sort, "live");
        String likeParam = createLikeParam(keyword);

        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchLiveResponse" +
                                "(l.id, l.title, m.name, m.channel, p.name) " +
                                "FROM Live l LEFT JOIN l.product p LEFT JOIN l.member m " +
                                "WHERE (l.title LIKE :keyword " +
                                "OR p.name LIKE :keyword " +
                                "OR m.channel LIKE :keyword) " +
                                "AND l.isActive = true " +
                                "AND l.liveOnAt IS NOT NULL " +
                                orderByClause
                        , SearchLiveResponseDto.class)
                .setParameter("keyword", likeParam)
                .getResultList();

    }

    public List<SearchShortpingResponseDto> findAllByNameForShortping(String keyword, String sort) {

        String orderByClause = getOrderByClause(sort, "shortping");
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
                        , SearchShortpingResponseDto.class)
                .setParameter("keyword", likeParam)
                .getResultList();

    }

    /** 정렬 조건 (인기순/최신순) - popularity/latest */
    private String getOrderByClause(String sort, String classify) {

        //라이브인지 숏핑인지 구분
        if ("live".equals(classify)) {
            return "ORDER BY l." + (sort.equals("latest") ? "liveOnAt" : "likeCnt") + " DESC " ;
        } else {
            return "ORDER BY s." + (sort.equals("latest") ? "createdAt" : "likeCnt") + " DESC ";
        }

    }

    /** keyword 로 검색 */
    private String createLikeParam(String keyword) {

        return "%" + keyword + "%";

    }

}