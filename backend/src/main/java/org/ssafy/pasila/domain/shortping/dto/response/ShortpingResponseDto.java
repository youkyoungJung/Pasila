package org.ssafy.pasila.domain.shortping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShortpingResponseDto {

    @Schema(description = "숏핑 아이디")
    private String id;

    @Schema(description = "숏핑 제목")
    private String title;

    @Schema(description = "숏핑 영상 경로")
    private String videoUrl;

    @Schema(description = "좋아요 수")
    private Integer likeCnt;

    @Schema(description = "숏핑 생성 일시")
    private LocalDateTime createdAt;

    @Schema(description = "제품 아이디")
    private String productId;

    @Schema(description = "제품 이름")
    private String productName;

    @Schema(description = "제품 썸네일")
    private String thumbnail;

    @Schema(description = "제품 설명")
    private String description;

    @Schema(description = "카테고리 아이디")
    private Long categoryId;

    @Schema(description = "판매자 아이디")
    private Long sellerId;

    @Schema(description = "판매자 채널 이름")
    private String sellerChannelName;

}
