package org.ssafy.pasila.domain.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.member.entity.Member;

@Repository
@RequiredArgsConstructor
public class MemberRepository{
    private final EntityManager em;

    public Member findByMemberId(Long memberId){
        return em.find(Member.class, memberId);
    };

}
