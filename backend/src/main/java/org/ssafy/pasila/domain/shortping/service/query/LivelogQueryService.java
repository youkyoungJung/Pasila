package org.ssafy.pasila.domain.shortping.service.query;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.shortping.dto.response.LivelogResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Livelog;
import org.ssafy.pasila.domain.shortping.repository.LivelogQueryRepository;
import org.ssafy.pasila.domain.shortping.repository.LivelogRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivelogQueryService {
    private final LivelogRepository livelogRepository;
    private final LivelogQueryRepository livelogQueryRepository;

    public Livelog findById(Long id) {
        return livelogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 아이디에 대한 하이라이트가 존재하지 않습니다"));
    }

    public List<Livelog> findByLiveId(String id) {
        return livelogQueryRepository.findAllByLive(id);
    }

    public List<Livelog> saveAll(List<Livelog> livelogList) {
        return livelogRepository.saveAll(livelogList);
    }

    public void deleteAll(List<Livelog> livelogList) {
        livelogRepository.deleteAll(livelogList);
    }

    public void deleteAllByLiveId(String liveId) {
        livelogQueryRepository.deleteAllByLiveId(liveId);
    }

}
