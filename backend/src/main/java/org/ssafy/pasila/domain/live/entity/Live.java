package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Live {
    @Id
    private String id;

    private String title;

    // 해당 부분 네이밍 이렇게 가져가는거 어떨지?
    private LocalDateTime liveOnAt;

    private LocalDateTime liveOffAt;

    private String script;

    // 해당부분도 네이밍 url 붙이는게 어떨지 -> 직관적으로
    private String fullVideoUrl;

    // Shortping 테이블이 아닌 여기 있는 자체가 이상함
    private String shortVideoUrl;

    private Long likeCnt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean isOn;


    //private Long userId;
    //private String productId;


}
