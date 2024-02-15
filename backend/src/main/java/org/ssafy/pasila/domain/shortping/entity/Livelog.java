package org.ssafy.pasila.domain.shortping.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.ssafy.pasila.domain.live.entity.Live;

import java.time.LocalTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "livelog_seq_generator",
        sequenceName = "livelog_seq",
        initialValue = 61,
        allocationSize = 1 )
@Entity
@Table(name = "livelog")
public class Livelog {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "livelog_seq_generator")
    private Long id;

    private LocalTime start;

    private LocalTime end;

    @Column(length = 30)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "live_id")
    private Live live;

    public void setLive(Live live) {
        this.live = live;
        live.getLivelogs().add(this);
    }

    public Livelog(LocalTime start, LocalTime end, String title, Live live) {
        this.start = start;
        this.end = end;
        this.title = title;
        setLive(live);
    }
}
