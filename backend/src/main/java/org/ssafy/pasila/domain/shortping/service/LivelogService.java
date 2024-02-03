package org.ssafy.pasila.domain.shortping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequestDto;
import org.ssafy.pasila.domain.shortping.dto.response.LivelogResponseDto;
import org.ssafy.pasila.domain.shortping.dto.response.RecommendLivelogResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Livelog;
import org.ssafy.pasila.domain.shortping.repository.LivelogRepository;
import org.ssafy.pasila.domain.shortping.service.query.LivelogQueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivelogService {

    private final LivelogQueryService livelogQueryService;

    // 하이라이트 리스트 저장
    public List<Livelog> saveLivelogList(List<LivelogRequestDto> livelogRequests, Live live) {

        List<Livelog> livelogs = livelogRequests.stream()
                .map(livelogRequest -> livelogRequest.toEntity(live))
                .collect(Collectors.toList());
        livelogQueryService.saveAll(livelogs);
        return livelogs;

    }

    // 추천 하이라이트 리스트 저장
    public List<Livelog> saveRecommandLivelogList(List<RecommendLivelogResponseDto> livelogRequests, Live live) {

        List<Livelog> livelogs = livelogRequests.stream()
                .map(livelogRequest -> livelogRequest.toEntity(live))
                .collect(Collectors.toList());
        livelogQueryService.saveAll(livelogs);
        return livelogs;

    }

    // 하이라이트 리스트 삭제
    public void deleteLivelogListByLiveId(String liveId) {
        livelogQueryService.deleteAllByLiveId(liveId);
    }

    // Live 아이디로 하이라이트 리스트 조회
    public List<LivelogResponseDto> getLivelogListByLiveId(String liveId) {
        return livelogQueryService.findByLiveId(liveId).stream()
                .map(livelog -> LivelogResponseDto.of(livelog))
                .collect(Collectors.toList());
    }

}
