package org.ssafy.pasila.domain.shortping.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.ShortpingQueryRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortpingQueryService {
    private final ShortpingRepository shortpingRepository;
    private final ShortpingQueryRepository shortpingQueryRepository;

    // 숏핑 아이디로 잦기
    public Shortping findById(String id) {
        return shortpingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 숏핑이 존재하지 않습니다"));
    }

    // 숏핑 product 아이디로 찾기
    public Shortping findWithProductById() {
        return null;
    }

    // 숏핑 저장
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

}
