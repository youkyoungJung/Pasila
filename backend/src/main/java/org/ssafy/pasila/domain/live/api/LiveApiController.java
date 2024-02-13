package org.ssafy.pasila.domain.live.api;

import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Recording;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ApiCommonResponse;
import org.ssafy.pasila.domain.live.dto.ChatLogDto;
import org.ssafy.pasila.domain.live.dto.request.CreateLiveRequestDto;
import org.ssafy.pasila.domain.live.dto.request.CreateQsheetRequestDto;
import org.ssafy.pasila.domain.live.dto.response.CreateQsheetResponseDto;
import org.ssafy.pasila.domain.live.dto.response.LiveStatsResponseDto;
import org.ssafy.pasila.domain.live.entity.Chatbot;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.service.ChatbotService;
import org.ssafy.pasila.domain.live.service.LiveService;
import org.ssafy.pasila.domain.live.service.OpenviduService;
import org.ssafy.pasila.domain.member.dto.ChannelLiveDto;
import org.ssafy.pasila.domain.product.dto.ProductRequestDto;
import org.ssafy.pasila.domain.product.dto.ProductSellResponseDto;
import org.ssafy.pasila.domain.product.service.ProductService;
import org.ssafy.pasila.domain.search.dto.LiveByCategoryResponseDto;
import org.ssafy.pasila.domain.search.service.SearchService;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.redis.service.LiveRedisService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/live")
@Tag(name = "Live", description = "Live API")
public class LiveApiController {

    @Autowired
    private OpenviduService openviduService;

    private final GptClient gptService;

    private final LiveService liveService;

    private final LiveRedisService liveRedisService;

    private final ProductService productService;

    private final ChatbotService chatbotService;

    private final SearchService searchService;

    // Pair - SessionId, RecordingId
    private final Map<String, String> mapRecordings = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate template;

    @Operation(summary = "Reserve Live", description = "라이브 예약(제품, 챗봇, 라이브)")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiCommonResponse<?> reserveLive(@RequestPart(value = "live") CreateLiveRequestDto createLiveRequestDto,
                                            @RequestPart(value = "product") ProductRequestDto productRequestDto,
                                            @RequestPart(value = "image") MultipartFile image,
                                            @RequestPart(value = "chatbot") List<Chatbot> chatbotList,
                                            // 로그인 완료후 @RequestHeader로 변경 예정
                                            @RequestPart(value = "member") Long memberId) throws IOException {
        // 1. Product
        String productId = productService.saveProduct(productRequestDto, image);
        // 2. Live
        String liveId = liveService.saveLive(createLiveRequestDto, memberId, productId);
        // 3. Chatbot
        chatbotService.save(chatbotList, liveId);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), liveId);
    }

    @Operation(summary = "Update Reserved Live", description = "예약된 라이브 정보 업데이트(제품, 챗봇, 라이브)")
    @PutMapping(value = "/{liveId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiCommonResponse<?> updateLive(@PathVariable("liveId") String liveId,
                                           @RequestPart(value = "live") CreateLiveRequestDto createLiveRequestDto,
                                           @RequestPart(value = "product") ProductRequestDto productRequestDto,
                                           @RequestPart(value = "image", required = false) MultipartFile image,
                                           @RequestPart(value = "chatbot") List<Chatbot> chatbotList) throws IOException {
        // 1. Live
        Live live = liveService.updateLive(liveId, createLiveRequestDto);
        // 2. Product
        String productId = productService.updateProduct(live.getProduct().getId(), productRequestDto, image);
        // 3. Chatbot
        chatbotService.updateChatbot(liveId, chatbotList);

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), live.getId());
    }

    @Operation(summary = "Delete Live", description = "라이브 삭제")
    @DeleteMapping("/{liveId}")
    public ApiCommonResponse<?> deleteLive(@PathVariable("liveId")String id) {
        String liveId = liveService.deleteLive(id);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), liveId);
    }

    @Operation(summary = "Get Live Schedule", description = "일자별 라이브 목록 조회")
    @GetMapping("/{date}")
    public ApiCommonResponse<?> findLiveList(@PathVariable("date") LocalDate date) {
        List<ChannelLiveDto> results = liveService.getScheduledLiveByDate(date);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), results);
    }

    @Operation(summary = "Live List In Home", description = "메인화면에서 카테고리별 라이브 목록")
    @GetMapping("/summary")
    public ApiCommonResponse<?> findLiveByCategory(@RequestParam(defaultValue = "0") Long categoryId) {
        LiveByCategoryResponseDto result = searchService.serachLiveByCategory(categoryId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);
    }


    @Operation(summary = "Live On", description = "라이브 방송 시작")
    @PutMapping("/{liveId}/on")
    public ApiCommonResponse<?> liveOn(@PathVariable("liveId") String liveId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        // 1. Live 정보 업데이트
        liveService.updateLiveOn(liveId);
        // 2. 화면 녹화 시작
        Recording recording = openviduService.startRecording(liveId);
        mapRecordings.put(liveId, recording.getId());
        // 3. Redis
        liveRedisService.addLive(liveId, liveService.getLiveById(liveId).getTitle());

        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), liveId);
    }

    @Operation(summary = "Live Off", description = "라이브 방송 종료")
    @PutMapping("/{liveId}/off")
    public ApiCommonResponse<?> liveOff(@PathVariable("liveId") String liveId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        // 1. 화면 녹화 중단
        Recording recording = openviduService.stopRecording(mapRecordings.get(liveId));
        // 2. Live 정보 업데이트
        liveService.updateLiveOff(liveId, recording.getUrl(), liveRedisService.getLikeCnt(liveId));
        // 3. Redis
        liveRedisService.deleteLiveInRedis(liveId); // 좋아요 수
        int participantCnt = liveService.deleteParticipantInRedis(liveId); // 참여자
        // 4. mapRecording 삭제
        mapRecordings.remove(liveId);
        // 5. 라이브 종료 정보(좋아요 수, 라이브 시작 시간, 라이브 종료 시간, 총 방송 시간, 시청자 수)
        LiveStatsResponseDto liveStats = liveService.calcLiveStats(liveId, participantCnt);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), liveStats);
    }

    @Operation(summary = "Get ProductInfo", description = "라이브 방송시 필요한 정보를 반환합니다.")
    @GetMapping("/{liveId}/product")
    public ApiCommonResponse<?> findSellProduct(@PathVariable("liveId") String liveId) {
        String productId = liveService.getProductId(liveId);
        ProductSellResponseDto product = productService.getProductSell(productId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), product);
    }

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
    @MessageMapping("/join")
    @PreAuthorize("isAuthenticated()")
    public void joinLive(@RequestBody ChatLogDto chatLogDto) {

        int participantNum = liveService.joinLive(chatLogDto.getLiveId() , chatLogDto.getMemberId());
        template.convertAndSend("/num/" + chatLogDto.getLiveId(), participantNum);

    }

    @Operation(summary = "Exit Live", description = "라이브에 참여한 참여자를 감소시킵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공")
    })
    @MessageMapping("/exit")
    public void exitLive(@RequestBody ChatLogDto chatLogDto){

        int participantNum = liveService.exitLive(chatLogDto.getLiveId() , chatLogDto.getMemberId());
        template.convertAndSend("/num/" + chatLogDto.getLiveId(), participantNum);

    }

    @Operation(summary = "Get Top5 Question", description = "라이브 방송 중 상위 5개의 질문을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = List.class))})
    })
    @GetMapping("/question")
    public ApiCommonResponse<?> getTop5Question(@RequestParam String liveId) {

        List<String> result = liveService.getTop5Question(liveId);
        return ApiCommonResponse.successResponse(HttpStatus.OK.value(), result);

    }

}
