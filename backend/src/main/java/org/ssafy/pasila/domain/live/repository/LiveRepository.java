package org.ssafy.pasila.domain.live.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface LiveRepository extends JpaRepository<Live, String> {

    Optional<Live> findByLiveOffAtIsNotNullAndProduct_Id(String id);

    Optional<Live> findByProduct_Id(String id);

    Optional<Live> findByProduct_IdAndIsOnTrue(String id);

    List<Live> findByIsActiveTrueAndMember_Id(Long id);

    List<Live>findByIsActiveTrueAndLiveScheduledAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Live>findByIsActiveTrueAndLiveScheduledAtGreaterThanEqualAndLiveScheduledAtLessThanOrderByLiveScheduledAtAsc(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
