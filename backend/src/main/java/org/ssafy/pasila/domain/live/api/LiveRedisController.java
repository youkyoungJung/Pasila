package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.global.infra.redis.dto.LiveRedisDto;
import org.ssafy.pasila.global.infra.redis.service.LiveRedisService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/redis/live")
@Tag(name = "Live Redis", description = "Live Redis API")
@Slf4j
public class LiveRedisController {

    private final LiveRedisService liveRedisService;

    @Operation(summary = "Live Redis Save", description = "라이브 시작시 캐시에 저장합니다. (좋아요 수)")
    @PostMapping("/start")
    public ApiCommonResponse<?> createLive(@RequestParam String liveId, @RequestParam String title) {

        String saveId = liveRedisService.addLive(liveId, title);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), saveId);

    }

    @Operation(summary = "Live Redis Like cnt up", description = "라이브 별 좋아요 수 증가")
    @PutMapping("/{id}")
    public ApiCommonResponse<?> likeCntUp(@PathVariable String id) {

        int likeCnt = liveRedisService.addLike(id);
//        String value = "현재 좋아요 수: " + likeCnt;
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), likeCnt);

    }

    @Operation(summary = "get Live Redis Like cnt", description = "현재 라이브 좋아요 수 가져오기")
    @GetMapping("/{id}")
    public ApiCommonResponse<?> getLikeCnt(@PathVariable String id) {

        int likeCnt = liveRedisService.getLikeCnt(id);
//        String value = "현재 좋아요 수: " + likeCnt;
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), likeCnt);

    }

    @Operation(summary = "get Live Redis Like cnt Top 3", description = "현재 라이브 좋아요 수 Top 3가져오기")
    @GetMapping("/top3")
    public ApiCommonResponse<?> getLikeCntTop3() {

        List<LiveRedisDto> list = liveRedisService.getTop3LiveinRedis();
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), list);

    }

}