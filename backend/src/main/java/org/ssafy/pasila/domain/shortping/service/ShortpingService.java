package org.ssafy.pasila.domain.shortping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.auth.service.EncryptService;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveQueryRepository;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.dto.ChannelShortpingDto;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.product.service.ProductService;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequestDto;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequestDto;
import org.ssafy.pasila.domain.shortping.dto.response.LiveThumbnailResponse;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.ShortpingQueryRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;
import org.ssafy.pasila.domain.shortping.service.query.ShortpingQueryService;
import org.ssafy.pasila.global.infra.FFmpeg.FFmpegClient;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.Script;
import org.ssafy.pasila.global.infra.s3.S3Uploader;
import org.ssafy.pasila.global.util.FileStorageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ShortpingService {

    private final LivelogService livelogService;

    private final ProductService productService;

    private final ShortpingQueryService shortpingQueryService;

    private final LiveQueryRepository liveQueryRepository;

    private final LiveRepository liveRepository;

    private final ShortpingQueryRepository shortpingQueryRepository;

    private final ShortpingRepository shortpingRepository;

    private final GptClient gptService;

    private final ProductOptionRepository productOptionRepository;

    private final EncryptService encryptService;

    private final FFmpegClient ffmpegClient;

    private final S3Uploader s3Uploader;

    private final FileStorageUtil fileStorageUtil;

    @Transactional
    public int updateLikeCnt(String shortpingId){

        Shortping shortping = shortpingRepository.findById(shortpingId)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        shortping.addLike();

        return shortping.getLikeCnt();

    }

    @Transactional
    public String saveShortping(ShortpingRequestDto shortpingRequest, MultipartFile video) {
        Live live = liveRepository.findById(shortpingRequest.getLiveId())
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        if(shortpingQueryService.existByProductId(live.getProduct().getId())) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        String url = fileStorageUtil.upload(video, live.getId() + ".mp4", "shortping");
        url = "https://i10a402.p.ssafy.io/download/" + url;

        livelogService.deleteLivelogListByLiveId(live.getId());

        List<LivelogRequestDto> livelogRequestDtoList = shortpingRequest.getLivelogs();
        livelogService.saveLivelogList(livelogRequestDtoList, live);

        Product product = live.getProduct();
        Shortping shortping = shortpingRequest.toEntity(url, product);
        shortpingQueryService.save(shortping);

        return shortping.getId();
    }

    public ShortpingResponseDto getShortpingById(String id) {
        ShortpingResponseDto shortpingResponseDto = shortpingQueryService.findWithProductMember(id);
        Live live = liveRepository.findByProduct_Id(shortpingResponseDto.getId())
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(shortpingResponseDto.getId())
                .stream()
                .map(ProductOptionDto::new)
                .toList();
        shortpingResponseDto.setOptions(options);
        shortpingResponseDto.setLiveId(live.getId());
        shortpingResponseDto.setAccount(encryptService.decryptAccount(shortpingResponseDto.getAccount()));
        return shortpingResponseDto;
    }


    // 추천 하이라이트 저장
    public void saveRecommandHighlight(String liveId) {

        Live live = liveRepository.findById(liveId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        byte[] file = fileStorageUtil.liveDownloadUrl(live.getFullVideoUrl());

        // 영상에서 하이라이트 가져오기
        List<RecommendLivelogResponseDto> highlights = getHighlightList(file);

        // 하이라이트 저장
        livelogService.saveRecommandLivelogList(highlights, live);
    }



    // 영상에서 하이라이트 뽑기
    public List<RecommendLivelogResponseDto> getHighlightList(byte[] file) {

        byte[] audioFilebytes = ffmpegClient.convertAudio(file);
        List<Script> segments = gptService.speechToText(audioFilebytes).getSegments();

        if(segments == null || segments.isEmpty()) {
            return new ArrayList<>();
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

        Live live = liveRepository.findById(id)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        // 라이브 영상 파일 이름 가져오기
        String liveUrl = live.getFullVideoUrl();
        byte[] liveVideo = fileStorageUtil.liveDownloadUrl(liveUrl);

        // 라이브 영상 썸네일 뽑기
        List<String> thumbnails = ffmpegClient.convertImages(liveVideo);

        return LiveThumbnailResponse.builder()
                .liveUrl(liveUrl)
                .thumbnails(thumbnails)
                .build();

    }

    public List<ChannelShortpingDto> getTop5ByCategoryOrderByLikeCnt(Long categoryId) {
        return shortpingQueryRepository.findTop5ByCategoryOrderByLikeCnt(categoryId);
    }
}
