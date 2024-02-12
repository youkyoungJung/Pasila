package org.ssafy.pasila.global.infra.redis.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.ssafy.pasila.global.util.TimeUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@RedisHash(value = "chat")
@AllArgsConstructor
public class ChatRedis implements Serializable {
    @Id
    private String liveId;

    private String memberName;

    private String message;

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "[" + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "] " + memberName + ": " + message;
    }
}
