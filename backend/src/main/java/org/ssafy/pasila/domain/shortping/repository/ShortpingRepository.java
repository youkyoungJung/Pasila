package org.ssafy.pasila.domain.shortping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

@Repository
public interface ShortpingRepository extends JpaRepository<Shortping, String> {
}
