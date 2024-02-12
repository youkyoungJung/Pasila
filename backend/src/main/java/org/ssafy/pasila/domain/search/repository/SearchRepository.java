package org.ssafy.pasila.domain.search.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto;
import org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final EntityManager em;

    public Page<SearchLiveResponseDto> findAllForLive(String keyword, String sort, Pageable pageable, Long lastItemId) {
        String orderByClause = getOrderByClause(sort, "live");
        String likeParam = createLikeParam(keyword);

        // 이전 페이지의 마지막 아이템의 likeCnt 또는 liveOnAt을 가져옴
        Integer lastLikeCnt = null;
        LocalDateTime lastLiveOnAt = null;
        if ("popularity".equals(sort)) {
            lastLikeCnt = getLastLikeCnt(lastItemId);
        } else if ("latest".equals(sort)) {
            lastLiveOnAt = getLastLiveOnAt(lastItemId);
        }
        // 쿼리 수행
        TypedQuery<SearchLiveResponseDto> query = em.createQuery(
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
                                (lastLikeCnt != null ? "AND l.likeCnt < :lastLikeCnt " : "") + // 이전 페이지의 마지막 likeCnt보다 작은 데이터를 가져옴
                                (lastLiveOnAt != null ? "AND l.liveOnAt < :lastLiveOnAt " : "") + // 이전 페이지의 마지막 liveOnAt보다 작은 데이터를 가져옴
                                (lastItemId != null ? "AND l.id != :lastItemId " : "") + // lastItemId와 동일하지 않은 데이터만 가져옴
                                "GROUP BY l.id, l.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                                orderByClause
                        , SearchLiveResponseDto.class)
                .setParameter("keyword", likeParam);


        // lastLikeCnt가 null이 아닌 경우에만 해당 파라미터를 설정
        if (lastLikeCnt != null) {
            query.setParameter("lastLikeCnt", lastLikeCnt);
        }

        // lastLiveOnAt가 null이 아닌 경우에만 해당 파라미터를 설정
        if (lastLiveOnAt != null) {
            query.setParameter("lastLiveOnAt", lastLiveOnAt);
        }

        if (lastItemId != null) {
            query.setParameter("lastItemId", lastItemId);
        }

        // 페이지네이션 적용
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // 결과 반환
        List<SearchLiveResponseDto> resultList = query.getResultList();
        long totalCount = getTotalCount(keyword); // 총 결과 수 조회
        return new PageImpl<>(resultList, pageable, totalCount);
    }

    // 이전 페이지의 마지막 아이템의 likeCnt를 가져오는 메서드
    private Integer getLastLikeCnt(Long lastItemId) {
        if (lastItemId == null) {
            return null; // lastItemId가 null이면 null 반환
        }
        // lastItemId를 사용하여 해당 아이템의 likeCnt를 조회하여 반환
        Live lastItem = em.find(Live.class, lastItemId);
        return lastItem != null ? lastItem.getLikeCnt() : null;
    }

    // 이전 페이지의 마지막 아이템의 liveOnAt을 가져오는 메서드
    private LocalDateTime getLastLiveOnAt(Long lastItemId) {
        if (lastItemId == null) {
            return null; // lastItemId가 null이면 null 반환
        }
        // lastItemId를 사용하여 해당 아이템의 liveOnAt을 조회하여 반환
        Live lastItem = em.find(Live.class, lastItemId);
        return lastItem != null ? lastItem.getLiveOnAt() : null;
    }

    // 총 결과 수 조회
    private long getTotalCount(String keyword) {
        String likeParam = createLikeParam(keyword);
        String countQuery = "SELECT COUNT(*) FROM Live l " +
                "LEFT JOIN l.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN l.member m " +
                "WHERE (l.title LIKE :keyword " +
                "OR p.name LIKE :keyword " +
                "OR m.channel LIKE :keyword) " +
                "AND l.isActive = true " +
                "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                "AND l.liveOffAt IS NOT NULL";
        TypedQuery<Long> query = em.createQuery(countQuery, Long.class);
        query.setParameter("keyword", likeParam);
        return query.getSingleResult();
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