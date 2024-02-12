package org.ssafy.pasila.global.infra.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.ssafy.pasila.global.infra.redis.entity.LiveRedis;

public interface LiveRedisRepository extends CrudRepository<LiveRedis, String> {

    Iterable<LiveRedis> findTop3ByOrderByLikeCntDesc();

}
