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
import org.ssafy.pasila.domain.shortping.entity.Shortping;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    private final EntityManager em;

    public Page<SearchLiveResponseDto> findAllForLive(String keyword, String sort, Pageable pageable, Long lastItemId, Long categoryId) {
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
        String jpql =
                "SELECT new org.ssafy.pasila.domain.search.dto.SearchLiveResponseDto" +
                        "(l.id, l.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                        "FROM Live l " +
                        "LEFT JOIN l.product p " +
                        "LEFT JOIN p.productOptions po " +
                        "LEFT JOIN l.member m " +
                        "WHERE l.isActive = true " +
                        "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                        "AND l.liveOffAt IS NOT NULL ";

        if(categoryId != null && categoryId != 0) {
            jpql += "AND p.category.id = :categoryId ";
        }
        // 키워드가 있는 경우에만 검색 조건 추가
        else if (keyword != null && !keyword.isEmpty()) {
            jpql += "AND (l.title LIKE :keyword " +
                    "OR p.name LIKE :keyword " +
                    "OR m.channel LIKE :keyword) ";
        }

        // 마지막 likeCnt 또는 liveOnAt에 따른 조건 추가
        if (lastLikeCnt != null) {
            jpql += "AND l.likeCnt < :lastLikeCnt ";
        }
        if (lastLiveOnAt != null) {
            jpql += "AND l.liveOnAt < :lastLiveOnAt ";
        }

        // lastItemId에 따른 조건 추가
        if (lastItemId != null) {
            jpql += "AND l.id != :lastItemId ";
        }

        jpql += "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                "GROUP BY l.id, l.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                orderByClause;


        TypedQuery<SearchLiveResponseDto> query = em.createQuery(jpql, SearchLiveResponseDto.class);

        //카테고리가 있는 경우에만 파라미터 설정
        if(categoryId != null && categoryId != 0) {
            query.setParameter("categoryId", categoryId);
        }

        // 키워드가 있는 경우에만 파라미터 설정
        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", likeParam);
        }

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
        long totalCount = getLiveTotalCount(keyword); // 총 결과 수 조회
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
    private long getLiveTotalCount(String keyword) {
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

    // 총 결과 수 조회
    private long getShortTotalCount(String keyword) {
        String likeParam = createLikeParam(keyword);
        String countQuery = "SELECT COUNT(*) FROM Shortping s " +
                "LEFT JOIN s.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN p.member m " +
                "WHERE (s.title LIKE :keyword " +
                "OR p.name LIKE :keyword " +
                "OR m.channel LIKE :keyword) " +
                "AND s.isActive = true " +
                "AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) ";

        TypedQuery<Long> query = em.createQuery(countQuery, Long.class);
        query.setParameter("keyword", likeParam);
        return query.getSingleResult();
    }



    public Page<SearchShortpingResponseDto> findAllShortping(Long categoryId, String keyword, Pageable pageable, String sort, Long lastItemId) {
        String orderByClause = getOrderByClause(sort, "shortping");
        String likeParam = createLikeParam(keyword);

        // 이전 페이지의 마지막 아이템의 likeCnt 또는 liveOnAt을 가져옴
        Integer lastShortpingLikeCnt = null;
        LocalDateTime lastShortpingCreatedAt = null;

        if ("popularity".equals(sort)) {
            lastShortpingLikeCnt = getLastShortpingLikeCnt(lastItemId);
        } else if ("latest".equals(sort)) {
            lastShortpingCreatedAt = getLastShortpingCreatedAt(lastItemId);
        }

        String jpql = "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto" +
                "(s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                "FROM Shortping s " +
                "LEFT JOIN s.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN p.member m " +
                "WHERE s.isActive = true ";

        if(categoryId != null && categoryId != 0) {
            jpql += "AND p.category.id = :categoryId ";

        } else if(keyword != null && !keyword.isEmpty()) {
            jpql += "AND (s.title LIKE :keyword " +
                    "OR p.name LIKE :keyword " +
                    "OR m.channel LIKE :keyword) ";
        }

        // 마지막 likeCnt 또는 liveOnAt에 따른 조건 추가
        if (lastShortpingLikeCnt != null) {
            jpql += "AND s.likeCnt < :lastShortpingLikeCnt ";
        }
        if (lastShortpingCreatedAt != null) {
            jpql += "AND s.createdAt < :lastShortpingCreatedAt ";
        }

        // lastItemId에 따른 조건 추가
        if (lastItemId != null) {
            jpql += "AND s.id != :lastItemId ";
        }

        jpql += " AND po.discountPrice = (SELECT MIN(po2.discountPrice) FROM ProductOption po2 WHERE po2.product.id = p.id) " +
                " GROUP BY s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name " +
                orderByClause;

        TypedQuery<SearchShortpingResponseDto> query = em.createQuery(jpql, SearchShortpingResponseDto.class);

        if(categoryId != null && categoryId != 0) {
            query.setParameter("categoryId", categoryId);
        } else if(keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", likeParam);
        }

        // lastLikeCnt가 null이 아닌 경우에만 해당 파라미터를 설정
        if (lastShortpingLikeCnt != null) {
            query.setParameter("lastShortpingLikeCnt", lastShortpingLikeCnt);
        }

        // lastLiveOnAt가 null이 아닌 경우에만 해당 파라미터를 설정
        if (lastShortpingCreatedAt != null) {
            query.setParameter("lastShortpingCreatedAt", lastShortpingCreatedAt);
        }

        if (lastItemId != null) {
            query.setParameter("lastItemId", lastItemId);
        }

        // 페이지네이션 적용
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // 결과 반환
        List<SearchShortpingResponseDto> resultList = query.getResultList();
        long totalCount = getShortTotalCount(keyword); // 총 결과 수 조회
        return new PageImpl<>(resultList, pageable, totalCount);
    }

    private LocalDateTime getLastShortpingCreatedAt(Long lastItemId) {

        if (lastItemId == null) {
            return null; // lastItemId가 null이면 null 반환
        }
        // lastItemId를 사용하여 해당 아이템의 createdAt을 조회하여 반환
        Shortping lastItem = em.find(Shortping.class, lastItemId);
        return lastItem != null ? lastItem.getCreatedAt() : null;

    }

    private Integer getLastShortpingLikeCnt(Long lastItemId) {
        if (lastItemId == null) {
            return null; // lastItemId가 null이면 null 반환
        }
        Shortping lastItem = em.find(Shortping.class, lastItemId);
        return lastItem != null ? lastItem.getLikeCnt() : null;
    }

    public List<SearchShortpingResponseDto> top5Shortping(Long categoryId) {

        String jpql = "SELECT new org.ssafy.pasila.domain.search.dto.SearchShortpingResponseDto" +
                "(s.id, s.title, m.id, m.channel, m.profile, p.id, p.thumbnail, p.name, MIN(po.price), MIN(po.discountPrice)) " +
                "FROM Shortping s " +
                "LEFT JOIN s.product p " +
                "LEFT JOIN p.productOptions po " +
                "LEFT JOIN p.member m " +
                "WHERE s.isActive = true";

        if(categoryId != null && categoryId != 0) {
            jpql += "AND p.category.id = :categoryId ";
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

/*


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


* */