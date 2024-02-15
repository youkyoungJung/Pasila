package org.ssafy.pasila.domain.shortping.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.search.dto.ShortpingByCategoryResponseDto;
import org.ssafy.pasila.domain.search.service.SearchService;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequestDto;
import org.ssafy.pasila.domain.shortping.dto.response.LiveThumbnailResponse;
import org.ssafy.pasila.domain.shortping.dto.response.LivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.service.LivelogService;
import org.ssafy.pasila.domain.shortping.service.ShortpingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/shortping")
@Tag(name = "Shortping", description = "Shortping API")
public class ShortpingApiControlller {

    private final ShortpingService shortpingService;

    private final LivelogService livelogService;

    private final SearchService searchService;

    @Operation(summary = "Create Shortping", description = "숏핑을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiCommonResponse<?> shortpingList(@RequestPart(value = "video") MultipartFile video, @RequestPart(value = "shortping") ShortpingRequestDto shortpingRequest) {

        String shortpingId = shortpingService.saveShortping(shortpingRequest, video);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "create success: " + shortpingId);

    }

    @Operation(summary = "Get Shortping Detail", description = "Id에 해당하는 숏핑 상세 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ShortpingResponseDto.class))})
    })
    @GetMapping("/{id}")
    public ApiCommonResponse<?> shortpingDetail(@PathVariable String id) {

        ShortpingResponseDto shortpingResponse = shortpingService.getShortpingById(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), shortpingResponse);

    }

    @Operation(summary = "Get Highlight List", description = "Live ID에 해당하는 하이라이트 리스트를 가져옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RecommendLivelogResponseDto.class)))})
    })
    @GetMapping("/highlight")
    public ApiCommonResponse<?> getLivelogList(@RequestParam(value = "live_id") String liveId) {

        List<LivelogResponseDto> livelogResponseDtoList = livelogService.getLivelogListByLiveId(liveId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), livelogResponseDtoList);

    }

    @Operation(summary = "Delete Shortping", description = "Id에 해당하는 숏핑을 삭제합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/{id}")
    public ApiCommonResponse<?> deleteShortping(@PathVariable String id) {

        shortpingService.deleteShortping(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), "delete success");

    }

    @Operation(summary = "Get Live and thumbnail List", description = "라이브 영상 및 썸네일 리스트 url을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LiveThumbnailResponse.class))})
    })
    @GetMapping("/thumbnail")
    public ApiCommonResponse<?> getThumbnailList(@RequestParam String liveId) {

        LiveThumbnailResponse liveThumbnailResponse = shortpingService.getThumbnailList(liveId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), liveThumbnailResponse);

    }

    @GetMapping("/summary")
    public ApiCommonResponse<?> getShortpingByCategory(@RequestParam(defaultValue = "0") Long categoryId) {

        ShortpingByCategoryResponseDto result = searchService.searchShortpingByCategory(categoryId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);

    }

    @PutMapping("/addLike")
    public ApiCommonResponse<?> LikeCntUp(@RequestParam String shortpingId) {

        int likeCnt = shortpingService.updateLikeCnt(shortpingId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), likeCnt);

    }
}
