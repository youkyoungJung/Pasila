package org.ssafy.pasila.domain.shortping.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.shortping.dto.response.ShortpingResponseDto;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.ShortpingQueryRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortpingQueryService {
    private final ShortpingRepository shortpingRepository;
    private final ShortpingQueryRepository shortpingQueryRepository;

    public Shortping findById(String id) {
        return shortpingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 숏핑이 존재하지 않습니다"));
    }

    public Shortping findByProductId(String id) {
        return shortpingQueryRepository.findByProductId(id);
    }

    public Boolean existByProductId(String id) {
        return shortpingQueryRepository.existByProductId(id);
    }

    public Shortping save(Shortping shortping) {
        return shortpingRepository.save(shortping);
    }

    public List<Shortping> saveAll(List<Shortping> shortpingList) {
        return shortpingRepository.saveAll(shortpingList);
    }

    public void deleteById(String id) {
        shortpingRepository.deleteById(id);
    }

    public void deleteAll(List<Shortping> shortpingList) {
        shortpingRepository.deleteAll(shortpingList);
    }

    public ShortpingResponseDto findWithProductMember(String id) {
        return shortpingQueryRepository.findWithProductMember(id);
    }

}
