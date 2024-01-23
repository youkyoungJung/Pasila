package org.ssafy.pasila.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByMemberId(Long memberId);

}
