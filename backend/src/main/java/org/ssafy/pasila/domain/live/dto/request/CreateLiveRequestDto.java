package org.ssafy.pasila.domain.live.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.product.entity.Product;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateLiveRequestDto {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "라이브 예정일")
    private LocalDateTime liveScheduledAt;

    @Schema(description = "대본")
    private String script;
}
