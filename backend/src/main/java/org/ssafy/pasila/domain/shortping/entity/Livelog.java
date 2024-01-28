package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssafy.pasila.domain.live.entity.Live;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "livelog")
public class Livelog {
    @Id @GeneratedValue
    private Long id;

    private LocalTime start;

    private LocalTime end;

    @Column(length = 30)
    private String title;

    @Column(length = 30)
    private String subtitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    public void setLive(Live live) {
        this.live = live;
        live.getLivelogs().add(this);
    }

}
