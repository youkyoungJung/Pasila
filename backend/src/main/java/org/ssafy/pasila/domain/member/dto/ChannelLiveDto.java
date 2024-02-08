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

    /* MEMBER */

    @Schema(description = "회원 Id")
    private Long memberId;

    @Schema(description = "채널명")
    private String channel;

    @Schema(description = "프로필")
    private String profile;

    /* PRODUCT */

    @Schema(description = "제품 Id")
    private String productId;

    @Schema(description = "썸네일")
    private String thumbnail;

    /* PRODUCTOPTION */

    @Schema(description = "옵션 Id")
    private  Long optionId;

    @Schema(description = "정가")
    private Integer price;

    @Schema(description = "할인가")
    private Integer discountPrice;

    /* SHORTPING */

    @Schema(description = "숏핑 Id")
    private String shortpingId;

}
