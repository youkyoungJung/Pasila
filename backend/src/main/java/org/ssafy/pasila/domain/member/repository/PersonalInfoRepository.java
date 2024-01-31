package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.ChannelDTO;
import org.ssafy.pasila.domain.member.dto.PersonalInfoDTO;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository {

    private final EntityManager em;

    public PersonalInfoDTO findById(Long id) {
        return em.createQuery("SELECT new org.ssafy.pasila.domain.member.dto.PersonalInfoDTO " +
                "(m.email, m.name, m.channel, m.phone, m.address, m.addressDetail, " +
                        "m.gender, m.birth, m.bank, m.account, m.profile) " +
                "FROM Member m " +
                "WHERE m.id = :memberId", PersonalInfoDTO.class)
                .setParameter("memberId", id).getSingleResult();
    }
}