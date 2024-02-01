package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.PersonalInfoDto;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository {

    private final EntityManager em;

    public PersonalInfoDto findById(Long id) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.PersonalInfoDTO " +
                "(m.email, m.name, m.channel, m.phone, m.address, m.addressDetail, " +
                        "m.gender, m.birth, m.bank, m.account, m.profile) " +
                "FROM Member m " +
                "WHERE m.id = :memberId", PersonalInfoDto.class)
                .setParameter("memberId", id).getSingleResult();
    }
}