package org.ssafy.pasila.global.infra.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.pasila.global.infra.redis.entity.Live;

public interface LiveRedisRepository extends CrudRepository<Live, String> {
}
