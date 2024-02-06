package org.ssafy.pasila.domain.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
public class SearchShortpingResponseDto {

    @Schema(description = "숏핑 id")
    private String id;

    @Schema(description = "숏핑 제목")
    private String title;

    @Schema(description = "판매자 id")
    private Long sellerId;

    @Schema(description = "판매자 채널명")
    private String channel;

    @Schema(description = "판매자 프로필 url")
    private String profileUrl;

    @Schema(description = "제품 id")
    private String productId;

    @Schema(description = "제품 썸네일 url")
    private String productThumbnailUrl;

    @Schema(description = "제품 이름")
    private String productName;

    //옵션 중 가장 가격 낮은 순
    @Schema(description = "정가")
    private int price;

    @Schema(description = "할인가")
    private int discountPrice;

}