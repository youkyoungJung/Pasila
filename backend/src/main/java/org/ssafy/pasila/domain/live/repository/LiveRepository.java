package org.ssafy.pasila.domain.live.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;

@Repository
public interface LiveRepository extends JpaRepository<Live, String> {
}
