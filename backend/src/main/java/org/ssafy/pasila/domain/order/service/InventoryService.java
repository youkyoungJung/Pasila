package org.ssafy.pasila.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger();

    private final ProductOptionRepository productOptionRepository;


}
