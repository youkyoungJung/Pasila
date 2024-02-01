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
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequest;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequest;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Livelog;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.LivelogRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;
import org.ssafy.pasila.global.infra.FFmpeg.FFmpegClient;
import org.ssafy.pasila.global.infra.gpt3.GptClient;
import org.ssafy.pasila.global.infra.gpt3.model.Script;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShortpingService {

    private final LiveQueryRepository liveQueryRepository;

    private final ShortpingRepository shortpingRepository;

    private final ProductRepository productRepository;

    private final LivelogRepository livelogRepository;

    private final GptClient gptService;

    private final FFmpegClient ffmpegClient;

    @Transactional
    public Shortping saveShortping(ShortpingRequest shortpingRequest) {
        Product product = getProductById(shortpingRequest.getProductId());
        Live live = liveQueryRepository.findByProductId(product.getId());
        List<Livelog> livelogs = saveLivelog(shortpingRequest.getLivelogs(), live);

        // 영상 생성


        // 숏핑 생성
        Shortping shortping = Shortping.createShortping(shortpingRequest.getTitle(), "아무경로", product);
        return shortpingRepository.save(shortping);
    }

    // 하이라이트 리스트 저장
    public List<Livelog> saveLivelog(List<LivelogRequest> livelogRequests, Live live) {
        List<Livelog> livelogs = livelogRequests.stream()
                .map(livelogRequest -> livelogRequest.toEntity(live))
                .collect(Collectors.toList());
        livelogRepository.saveAll(livelogs);
        return livelogs;
    }


    // 하이라이트 추천
    public List<RecommendLivelogResponseDto> getHighlightList(MultipartFile file) {
        try {
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
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            throw new RestApiException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

    private Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 상품이 존재하지 않습니다"));
    }

}
