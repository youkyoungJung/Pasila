package org.ssafy.pasila.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.pasila.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m" +
            "FROM Member m" +
            "WHERE email = :email")
    Member findByMemberEmail();
}
