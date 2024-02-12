package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
public class SearchLiveResponseDto {

    @Schema(description = "라이브 id")
    private String liveId;

    @Schema(description = "라이브 제목")
    private String title;

    @Schema(description = "판매자 id")
    private Long sellerId;

    @Schema(description = "판매자 채널명")
    private String channel;

    @Schema(description = "판매자 프로필 url")
    private String profile;

    @Schema(description = "제품 id")
    private String productId;

    @Schema(description = "제품 썸네일 url")
    private String productThumbnail;

    @Schema(description = "제품 이름")
    private String productName;

    //옵션 중 가장 가격 낮은 순
    @Schema(description = "정가")
    private int price;

    @Schema(description = "할인가")
    private int discountPrice;

}