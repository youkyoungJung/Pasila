package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.shortping.entity.Shortping;

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

    @Column(name = "live_on_at")
    private LocalDateTime liveOnAt;

    @Column(name = "live_off_at")
    private LocalDateTime liveOffAt;

    private String script;

    @Column(name = "full_video_url")
    private String fullVideoUrl;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @Column(name = "is_on")
    private boolean isOn;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private LocalDateTime isActive;

//    @OneToOne
//    private Shortping shortping;


    //private Long userId;
    //private String productId;


}
