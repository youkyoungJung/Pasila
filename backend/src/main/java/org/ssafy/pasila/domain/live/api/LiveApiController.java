package org.ssafy.pasila.domain.live.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.live.dto.request.CreateQsheetRequestDto;
import org.ssafy.pasila.domain.live.dto.response.CreateQsheetResponseDto;
import org.ssafy.pasila.domain.live.service.LiveService;
import org.ssafy.pasila.global.infra.gpt3.GptClient;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/live")
@Tag(name = "Live", description = "Live API")
public class LiveApiController {

    private final GptClient gptService;

    private final LiveService liveService;

    @Operation(summary = "Create Qsheet", description = "큐시트를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateQsheetResponseDto.class))})
    })
    @PostMapping("/sheet")
    public ApiCommonResponse<?> createQsheet(@RequestBody CreateQsheetRequestDto request) {

        String qsheet = gptService.generateQsheet(
                "판매자",
                request.getProductName(),
                request.getDescription()
        );
        String qsheetStyle = gptService.chatStyle(request.getStyle(), qsheet);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), qsheetStyle);

    }

    @Operation(summary = "Create Qsheet Sample", description = "큐시트 샘플을 제공합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateQsheetResponseDto.class))})
    })
    @GetMapping("/sheet/example")
    public ApiCommonResponse<?> createQsheetTest() {

        String result = "큐시트:\n" +
                "\n" +
                "1. 오프닝 / 인사\n" +
                "- 안녕하구먼! 여러분의 쇼호스트 판매자 할아버지가 오늘은 제주 서귀포 노지 하우스 타이벡 감귤 황금향 레드향 가정용 선물세트를 소개해주겠네.\n" +
                "\n" +
                "2. 상품소개 / 라이브 방송 혜택 안내\n" +
                "- 이 감귤 선물세트는 제주 산지에서 직접 수확하고 꼼꼼히 선별하여 보내주는 것이여. 제철인 11-2월에 수확한 노지 조생 감귤로, 새콤 달콤하면서도 깊고 진한 맛이 특징이라네. 이번 라이브 방송에서만 특별한 할인 혜택을 받을 수 있으니까 놓치지 마시게.\n" +
                "\n" +
                "3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교\n" +
                "- 이 선물세트는 꼬마, 골프공, 소과, 로얄과, 중대과로 구성되어 있고, 각각의 크기는 다양한 용도로 활용할 수 있도록 다양하게 구성되었네. 포장은 신선도를 유지하기 위해 신경써서 하였고, 상품의 크기 비교도 함께 안내해주겠네.\n" +
                "\n" +
                "4. 제품의 특징 강조\n" +
                "- 이 감귤 선물세트는 제주 해풍으로 키운 신선한 감귤로, 깊고 진한 감귤 본연의 풍부한 맛을 느낄 수 있고, 또한, 당도 선별로 균일한 맛과 편차없는 최고의 상품성을 자랑하고 있네. 믿고 먹을 수 있는 제철 감귤로, 맛에 대한 보장과 안심을 드리겠네.\n" +
                "\n" +
                "5. 제품을 활용할 수 있는 여러 방법 / 사용하는 용도\n" +
                "- 이 감귤 선물세트는 다양한 방법으로 활용할 수 있는 것이여. 신선한 감귤을 그대로 드셔도 좋고, 주스나 요거트에 넣어 즐기실 수도 있고, 감귤을 간편하게 꺼내서 간식으로 즐기거나, 선물로 주셔도 좋을 것이여.\n" +
                "\n" +
                "6. 제품 사용 방법 안내\n" +
                "- 이 감귤 선물세트는 사용하기 매우 간편하다네. 포장을 열어 감귤을 꺼내서 바로 드실 수 있고, 감귤의 껍질을 벗기고, 신선한 맛을 즐기시면 될 것이여.\n" +
                "\n" +
                "7. 타사와의 차별점 설명\n" +
                "- 이 감귤 선물세트는 제주 산지에서 직접 수확하여 보내드리기 때문에, 신선도와 맛에 대한 자신감을 가지고 있고, 또한, 제주 해풍으로 키운 감귤로 인해 깊고 진한 맛을 느낄 수 있고, 타사와는 다르게 꼼꼼한 선별과 품질 관리로 최고의 상품성을 자랑하고 있네.\n" +
                "\n" +
                "8. 다시 한번 제품 구성과 가격 강조\n" +
                "- 이 감귤 선물세트는 꼬마, 골프공, 소과, 로얄과, 중대과로 구성되어 있고, 원래 가격은 500,000원이지만 이벤트 기간 동안만 420,000원에 판매하고 있기 때문에, 이번 기회를 놓치지 마시게.\n" +
                "\n" +
                "9. 마무리 / 마지막 안내\n" +
                "- 이제 많은 분들이 기다리시는 할인된 가격으로 제주 서귀포 노지 하우스 타이벡 감귤 황금향 레드향 가정용 선물세트를 구매하실 수 있고, 이번 기회를 놓치지 마시고, 지금 바로 구매해보시게!";
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);

    }

    @Operation(summary = "Join Live", description = "라이브에 참여한 참여자를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/join")
    public ApiCommonResponse<?> joinLive(@RequestParam String roomId , @RequestParam String memberId) {

        int participantNum = liveService.joinLive(roomId , memberId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), participantNum);

    }

    @Operation(summary = "Exit Live", description = "라이브에 참여한 참여자를 감소시킵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @DeleteMapping("/exit")
    public ApiCommonResponse<?> exitLive(@RequestParam String roomId , @RequestParam String memberId){

        int participantNum = liveService.exitLive(roomId , memberId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), participantNum);
        
    }

}
