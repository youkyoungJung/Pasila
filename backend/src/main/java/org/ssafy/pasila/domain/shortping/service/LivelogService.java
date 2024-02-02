package org.ssafy.pasila.domain.shortping.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequestDto;
import org.ssafy.pasila.domain.shortping.entity.Livelog;
import org.ssafy.pasila.domain.shortping.repository.LivelogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivelogService {

    private final LivelogRepository livelogRepository;

    // 하이라이트 리스트 저장
    public List<Livelog> saveLivelog(List<LivelogRequestDto> livelogRequests, Live live) {

        List<Livelog> livelogs = livelogRequests.stream()
                .map(livelogRequest -> livelogRequest.toEntity(live))
                .collect(Collectors.toList());
        livelogRepository.saveAll(livelogs);
        return livelogs;

    }

    // 하이라이트 리스트 삭제
}
