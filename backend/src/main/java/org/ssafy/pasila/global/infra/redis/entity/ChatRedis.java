package org.ssafy.pasila.global.infra.redis.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("chat")
@AllArgsConstructor
public class ChatRedis implements Serializable {
    @Id
    private String liveId;

    private String memberName;

    private String message;

    private LocalDateTime createdAt;
}
