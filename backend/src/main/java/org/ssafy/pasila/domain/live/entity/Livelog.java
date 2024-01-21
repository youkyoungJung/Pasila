package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "livelog")
public class Livelog {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    @Column(length = 30)
    private String title;

    @Column(length = 30)
    private String subtitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;
}
