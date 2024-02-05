package org.ssafy.pasila.domain.shortping.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class LiveThumbnailResponse {
    @Schema(description = "라이브 영상 url")
    private String liveUrl;

    @Schema(description = "썸네일 이미지 url 리스트")
    private List<String> thumbnails;
}
