package org.ssafy.pasila.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member , Long> {
    Member findByEmail(String email);

    Member findByChannel(String channel);

}