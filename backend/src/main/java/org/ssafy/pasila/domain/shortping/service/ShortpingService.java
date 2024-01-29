package org.ssafy.pasila.domain.shortping.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveQueryRepository;
import org.ssafy.pasila.domain.product.entity.Product;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.domain.shortping.dto.request.LivelogRequest;
import org.ssafy.pasila.domain.shortping.dto.request.ShortpingRequest;
import org.ssafy.pasila.domain.shortping.entity.Livelog;
import org.ssafy.pasila.domain.shortping.entity.Shortping;
import org.ssafy.pasila.domain.shortping.repository.LivelogRepository;
import org.ssafy.pasila.domain.shortping.repository.ShortpingRepository;

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

    private Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디에 대한 상품이 존재하지 않습니다"));
    }



}
