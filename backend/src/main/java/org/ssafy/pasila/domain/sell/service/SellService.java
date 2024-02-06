package org.ssafy.pasila.domain.sell.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.order.entity.Order;
import org.ssafy.pasila.domain.order.entity.Status;
import org.ssafy.pasila.domain.order.repository.OrderRepository;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ProductOptionRepository productOptionRepository;


    @Transactional
    public Long changeStatus(Long id, String status){

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        order.changeStatus(Enum.valueOf(Status.class, status));
        return order.getId();

    }
}
