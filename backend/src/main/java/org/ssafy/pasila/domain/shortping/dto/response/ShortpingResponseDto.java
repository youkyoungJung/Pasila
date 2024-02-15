package org.ssafy.pasila.domain.shortping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ShortpingResponseDto {

    @Schema(description = "제품 아이디")
    private String id;

    @Schema(description = "판매자 아이디")
    private Long sellerId;

    @Schema(description = "판매자 채널 이름")
    private String sellerChannelName;

    @Schema(description = "제품 이름")
    private String name;

    @Schema(description = "제품 설명")
    private String description;

    @Schema(description = "숏핑 생성 일시")
    private LocalDateTime createdAt;

    @Schema(description = "제품 썸네일")
    private String thumbnail;

    @Schema(description = "카테고리 아이디")
    private Long categoryId;

    @Schema(description = "제품 옵션 리스트")
    private List<ProductOptionDto> options;

    @Schema(description = "은행")
    private String bank;

    @Schema(description = "계좌번호")
    private String account;

    @Schema(description = "숏핑 제목")
    private String title;

    @Schema(description = "숏핑 영상 경로")
    private String videoUrl;

    @Schema(description = "좋아요 수")
    private Integer likeCnt;

    @Schema(description = "숏핑 아이디")
    private String shortpingId;

    @Schema(description = "라이브 아이디")
    private String liveId;

    public ShortpingResponseDto(String id, Long sellerId, String sellerChannelName, String name, String description, LocalDateTime createdAt, String thumbnail, Long categoryId, String bank, String account, String title, String videoUrl, Integer likeCnt, String shortpingId) {
        this.id = id;
        this.sellerId = sellerId;
        this.sellerChannelName = sellerChannelName;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
        this.bank = bank;
        this.account = account;
        this.title = title;
        this.videoUrl = videoUrl;
        this.likeCnt = likeCnt;
        this.shortpingId = shortpingId;
    }
}
