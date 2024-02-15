package org.ssafy.pasila.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ChannelShortpingDto {

    /* SHORTPING */

    @Schema(description = "숏핑 Id")
    private String id;

    @Schema(description = "숏핑 타이틀")
    private String title;

    @Schema(description = "숏핑 제작일")
    private LocalDateTime createdAt;

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

}
