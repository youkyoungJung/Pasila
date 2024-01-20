package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Shortping {
    @Id
    private String id;

    // private String productId;

    private String title;

    private Long likeCnt;

    @Column(name = "video_url")
    private String videoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime isActive;

}
