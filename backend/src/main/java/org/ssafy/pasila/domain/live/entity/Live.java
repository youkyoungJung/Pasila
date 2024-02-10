package org.ssafy.pasila.domain.live.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.sqids.Sqids;
import org.ssafy.pasila.domain.live.dto.request.CreateLiveRequestDto;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.shortping.entity.Livelog;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Live {
    @Id
    @Column(columnDefinition = "VARCHAR(10)")
    private String id;

    @PrePersist
    public void createUniqId() {

        Sqids sqids = Sqids.builder()
                .minLength(8)
                .build();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestampAsLong = timestamp.getTime();
        String newId = sqids.encode(Arrays.asList(timestampAsLong, 1L));

        this.id = newId;

    }

    @Column(length = 30)
    private String title;

    @Column(name = "live_scheduled_at")
    private LocalDateTime liveScheduledAt;

    @Column(name = "live_on_at")
    private LocalDateTime liveOnAt;

    @Column(name = "live_off_at")
    private LocalDateTime liveOffAt;

    @Lob
    private String script;

    @Column(name = "full_video_url", length = 2083)
    private String fullVideoUrl;

    @Column(name = "like_cnt")
    private Integer likeCnt;

    @Column(name = "is_on", columnDefinition = "TINYINT(1)")
    @ColumnDefault("false")
    private boolean isOn;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active", columnDefinition = "TINYINT(1)")
    @ColumnDefault("true")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Product product;

    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    private List<Livelog> livelogs = new ArrayList<>();

    @OneToMany(mappedBy = "live", cascade = CascadeType.ALL)
    private List<Chatbot> chatbots = new ArrayList<>();

    public static Live createLive(CreateLiveRequestDto createLiveRequestDto, Member member, Product product) {
        Live live = Live.builder()
                .title(createLiveRequestDto.getTitle())
                .liveScheduledAt(createLiveRequestDto.getLiveScheduledAt())
                .script(createLiveRequestDto.getScript())
                .member(member)
                .product(product)
                .build();
        live.setChatbots(new ArrayList<>());
        return live;
    }

    public void updateLive(CreateLiveRequestDto createLiveRequestDto) {
        this.title = createLiveRequestDto.getTitle();
        this.liveScheduledAt = createLiveRequestDto.getLiveScheduledAt();
        this.script = createLiveRequestDto.getScript();

    public void setLiveOn(){
        this.isOn = true;
        this.liveOnAt = LocalDateTime.now();
    }

    public void setLiveOff(String fullVideoUrl, int likeCnt){
        this.liveOffAt = LocalDateTime.now();
        this.isOn = false;
        this.fullVideoUrl = fullVideoUrl;
        this.likeCnt = likeCnt;
    }

}
