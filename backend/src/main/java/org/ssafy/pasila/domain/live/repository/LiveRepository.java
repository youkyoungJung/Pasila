package org.ssafy.pasila.domain.live.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.pasila.domain.live.entity.Live;

import java.util.Optional;

@Repository
public interface LiveRepository extends JpaRepository<Live, String> {

    Optional<Live> findByLiveOffAtIsNotNullAndProduct_Id(String id);

    Optional<Live> findByProduct_Id(String id);

    Optional<Live> findByProduct_IdAndIsOnTrue(String id);

}
