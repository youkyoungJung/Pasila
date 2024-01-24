package org.ssafy.pasila.domain.live.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.live.entity.Live;

public interface LiveRepository extends JpaRepository<Live, String> {
}
