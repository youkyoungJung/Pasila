package org.ssafy.pasila.domain.search.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final EntityManager em;

    public List<SearchLiveResponseDto> findAllForLive(String keyword, String sort) {
        String orderByClause = getOrderByClause(sort, "live");
        String likeParam = createLikeParam(keyword);

        return em.createQuery(
                       "SELECT new org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto" +
                        "(l.id, l.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                                "FROM Live l " +
                                "LEFT JOIN l.product p " +
                                "LEFT JOIN p.productOptions po " +
                                "LEFT JOIN l.member m " +
                                "WHERE (l.title LIKE :keyword " +
                                "OR p.name LIKE :keyword " +
                                "OR m.channel LIKE :keyword) " +
                                "AND l.isActive = true " +
                                "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                                "AND l.liveOffAt IS NOT NULL " +
                                "GROUP BY l.id, l.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                               orderByClause
                        , SearchLiveResponseDto.class)
                .setParameter("keyword", likeParam)
                .getResultList();

    }

    public List<SearchShortpingResponseDto> findAllForShortping(String keyword, String sort) {

        String orderByClause = getOrderByClause(sort, "shortping");
        String likeParam = createLikeParam(keyword);

        return em.createQuery(
                        "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto" +
                                "(s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                                "FROM Shortping s " +
                                "LEFT JOIN s.product p " +
                                "LEFT JOIN p.productOptions po " +
                                "LEFT JOIN p.member m " +
                                "WHERE (s.title LIKE :keyword " +
                                "OR p.name LIKE :keyword " +
                                "OR m.channel LIKE :keyword) " +
                                "AND s.isActive = true " +
                                "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                                "GROUP BY s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                                orderByClause
                        , SearchShortpingResponseDto.class)
                .setParameter("keyword", likeParam)
                .getResultList();

    }

    public List<SearchShortpingResponseDto> findAllShortpingByFilter(Long categoryId, String keyword, String sort) {
        String orderByClause = getOrderByClause(sort, "shortping");
        String likeParam = createLikeParam(keyword);

        String jpql = "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto" +
                "(s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                "FROM Shortping s " +
                "LEFT JOIN s.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN p.member m ";

        if(categoryId != null && categoryId != 0) {
            jpql += "WHERE p.category.id = :categoryId " +
                    "AND s.isActive = true " +
                    "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) ";

        } else if(keyword != null && !keyword.isEmpty()) {
            jpql += "WHERE (s.title LIKE :keyword " +
                    "OR p.name LIKE :keyword " +
                    "OR m.channel LIKE :keyword) " +
                    "AND s.isActive = true " +
                    "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) ";
        }

        jpql += "GROUP BY s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                orderByClause;

        TypedQuery<SearchShortpingResponseDto> query = em.createQuery(jpql, SearchShortpingResponseDto.class);

        if(categoryId != null && categoryId != 0) {
            query.setParameter("categoryId", categoryId);
        } else if(keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", likeParam);
        }

        return query.getResultList();
    }

    public List<SearchShortpingResponseDto> top5Shortping(Long categoryId) {

        String jpql = "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto" +
                "(s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                "FROM Shortping s " +
                "LEFT JOIN s.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN p.member m ";

        if(categoryId != null && categoryId != 0) {
            jpql += "WHERE p.category.id = :categoryId " +
                    "AND s.isActive = true ";
        } else {
            jpql += "WHERE s.isActive = true ";
        }

        jpql += "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                "AND s.createdAt >= :threshold " +
                "GROUP BY s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                "ORDER BY s.likeCnt DESC ";


        TypedQuery<SearchShortpingResponseDto> query = em.createQuery(jpql, SearchShortpingResponseDto.class);

        if(categoryId != null && categoryId != 0) {
            query.setParameter("categoryId", categoryId);
        }

        return query.setParameter("threshold", LocalDateTime.now().minusDays(3))
                .setMaxResults(5)
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