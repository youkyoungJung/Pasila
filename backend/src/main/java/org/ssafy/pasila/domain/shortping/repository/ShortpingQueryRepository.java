package org.ssafy.pasila.domain.shortping.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShortpingQueryRepository {

    private final EntityManager em;

    public List<ChannelShortpingDto> findTop5ByCategoryOrderByLikeCnt(Long categoryId) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.ChannelShortpingDto" +
                                "(s.id, s.title, s.createdAt, " +
                                "m.id, m.channel, m.profile, " +
                                "p.id, p.thumbnail, " +
                                "po.price, po.discountPrice) " +
                                "FROM Shortping s " +
                                "JOIN s.product p " +
                                "JOIN p.member m " +
                                "JOIN p.productOptions po " +
                                "WHERE po.id = " +
                                "(SELECT MIN(po2.id) FROM ProductOption po2 WHERE po2.product.id = p.id " +
                                "AND po2.price = " +
                                "(SELECT MIN(po3.price) FROM ProductOption po3 WHERE po3.product.id = p.id) " +
                                "AND po2.discountPrice = " +
                                "(SELECT MIN(po4.discountPrice) FROM ProductOption po4 WHERE po4.product.id = p.id)) " +
                                "AND p.category.id = :categoryId " +
                                "ORDER BY s.likeCnt DESC " +
                                "LIMIT 5", ChannelShortpingDto.class)
                .setParameter("categoryId", categoryId).getResultList();
    }

    public ShortpingResponseDto findWithProductMember(String id) {

        return em.createQuery(
                "select new org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto" +
                        "(p.id, m.id, m.channel, p.name, p.description, s.createdAt, p.thumbnail, c.id, m.bank, m.account, s.title, s.videoUrl, s.likeCnt, s.id)" +
                        " from Shortping s" +
                        " join s.product p" +
                        " join p.category c" +
                        " join p.member m" +
                        " where s.id=:shortpingId" +
                        " and p.isActive=true", ShortpingResponseDto.class)
                .setParameter("shortpingId", id)
                .getSingleResult();

    }

    public Shortping findByProductId(String id) {
        return em.createQuery(
                "select s from Shortping s" +
                        " where s.product.id =: productId", Shortping.class)
                .setParameter("productId", id)
                .getSingleResult();
    }

    public Boolean existByProductId(String id) {
        return em.createQuery(
                        "select exists (" +
                                "select s from Shortping s" +
                                " where s.product.id =: productId)", Boolean.class)
                .setParameter("productId", id)
                .getSingleResult();
    }
}
