package org.ssafy.pasila.global.infra.redis.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@Getter @Setter
@RedisHash(value = "live")
@AllArgsConstructor
@NoArgsConstructor
public class LiveRedis {

    @Id
    private String id;

    private String title;

    private int likeCnt;

    public void addLike(){
        this.likeCnt++;
    }

}