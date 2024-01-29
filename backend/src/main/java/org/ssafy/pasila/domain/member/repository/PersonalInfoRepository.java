package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.dto.response.PersonalInfoResponse;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository {

    private final EntityManager em;

    public PersonalInfoResponse findById(Long id) {
        return em.find(PersonalInfoResponse.class, id);
    }
}