package org.ssafy.pasila.domain.live.entity;

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
public class Livelog {
    @Id
    private Long id;
    private String liveId;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String subTitle;

}
