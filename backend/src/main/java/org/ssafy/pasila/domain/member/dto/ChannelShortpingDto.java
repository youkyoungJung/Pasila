package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChannelShortpingDto {

    /* SHORTPING */

    @Schema(description = "숏핑 Id")
    private String id;

    @Schema(description = "숏핑 타이틀")
    private String title;

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

    /* LIVE */

    @Schema(description = "라이브 Id")
    private String liveId;
}
