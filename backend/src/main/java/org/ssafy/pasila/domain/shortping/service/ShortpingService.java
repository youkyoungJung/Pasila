package org.ssafy.pasila.domain.shortping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveQueryRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.service.ProductService;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequestDto;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequestDto;
import org.ssafy.pasila.domain.shortping.dto.response.LiveThumbnailResponse;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.service.query.ShortpingQueryService;
import org.ssafy.pasila.global.infra.FFmpeg.FFmpegClient;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.Script;
import org.ssafy.pasila.global.infra.s3.S3Uploader;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShortpingService {

    private final LivelogService livelogService;

    private final ProductService productService;

    private final ShortpingQueryService shortpingQueryService;

    private final LiveQueryRepository liveQueryRepository;

    private final GptClient gptService;

    private final FFmpegClient ffmpegClient;

    private final S3Uploader s3Uploader;

    @Transactional
    public Shortping saveShortping(ShortpingRequestDto shortpingRequest, MultipartFile video) {
        String productId = shortpingRequest.getProductId();
        Live live = liveQueryRepository.findByProductId(productId);

        if(shortpingQueryService.existByProductId(productId)) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        // TODO: 영상 저장
        String url = "test";
        // url = s3Uploader.upload("test", video, "shortping");

        livelogService.deleteLivelogListByLiveId(live.getId());

        List<LivelogRequestDto> livelogRequestDtoList = shortpingRequest.getLivelogs();
        livelogService.saveLivelogList(livelogRequestDtoList, live);

        Product product = productService.getProductById(productId);
        Shortping shortping = shortpingRequest.toEntity(url, product);
        shortpingQueryService.save(shortping);

        return shortping;
    }

    public ShortpingResponseDto getShortpingById(String id) {
        return shortpingQueryService.findWithProductMember(id);
    }


    // 추천 하이라이트 저장
    public void saveRecommandHighlight(String productId) throws IOException {
        // TODO: 영상 가져오기
        MultipartFile file = null;

        // 영상에서 하이라이트 가져오기
        List<RecommendLivelogResponseDto> highlights = getHighlightList(file);

        // 하이라이트 저장
        Live live = liveQueryRepository.findByProductId(productId);
        livelogService.saveRecommandLivelogList(highlights, live);
    }



    // 영상에서 하이라이트 뽑기
    public List<RecommendLivelogResponseDto> getHighlightList(MultipartFile file) {

        byte[] audioFilebytes = ffmpegClient.convertAudio(file);
        List<Script> segments = gptService.speechToText(audioFilebytes).getSegments();

        if(segments == null || segments.isEmpty()) {
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        String result = "";
        for (Script script: segments) {
            result += script.toString();
        }

        return gptService.getHighlight(result);

    }

    @Transactional
    public void deleteShortping(String id) {
        Shortping shortping = shortpingQueryService.findById(id);
        shortpingQueryService.delete(shortping);
    }


    // 라이브 영상 & 썸네일
    public LiveThumbnailResponse getThumbnailList(String id) {
        try {
            Live live = liveQueryRepository.findByProductId(id);

            // 라이브 영상 파일 이름 가져오기
            String liveUrl = live.getFullVideoUrl();

            // TODO: 라이브 영상 가져오기
            byte[] liveVideo = null;

            // 라이브 영상 썸네일 뽑기
            List<String> thumbnails = ffmpegClient.convertImages(liveVideo);

            return LiveThumbnailResponse.builder()
                    .liveUrl(liveUrl)
                    .thumbnails(thumbnails)
                    .build();

        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }
}
