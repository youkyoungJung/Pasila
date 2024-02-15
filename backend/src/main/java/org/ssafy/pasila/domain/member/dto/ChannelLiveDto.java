package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ChannelLiveDto {

    /* LIVE */

    @Schema(description = "라이브 Id")
    private String liveId;

    @Schema(description = "라이브 제목")
    private String title;

    @Schema(description = "라이브 예정일")
    private LocalDateTime liveScheduledAt;

    @Schema(description = "라이브 시작일")
    private LocalDateTime liveOnAt;

    @Schema(description = "라이브 종료일")
    private LocalDateTime liveOffAt;

    /* MEMBER */

    @Schema(description = "회원 Id")
    private Long sellerId;

    @Schema(description = "채널명")
    private String channel;

    @Schema(description = "프로필")
    private String profileUrl;

    /* PRODUCT */

    @Schema(description = "제품 Id")
    private String productId;

    @Schema(description = "썸네일")
    private String productThumbnailUrl;

    /* PRODUCTOPTION */

    @Schema(description = "정가")
    private Integer price;

    @Schema(description = "할인가")
    private Integer discountPrice;

    /* SHORTPING */

    @Schema(description = "숏핑 Id")
    private String shortpingId;

}
