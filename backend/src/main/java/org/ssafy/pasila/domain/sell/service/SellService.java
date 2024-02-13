package org.ssafy.pasila.domain.sell.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.pasila.domain.apihandler.ErrorCode;
import org.ssafy.pasila.domain.apihandler.RestApiException;
import org.ssafy.pasila.domain.live.entity.Live;
import org.ssafy.pasila.domain.live.repository.LiveRepository;
import org.ssafy.pasila.domain.member.entity.Member;
import org.ssafy.pasila.domain.member.repository.MemberRepository;
import org.ssafy.pasila.domain.order.dto.OrderDto;
import org.ssafy.pasila.domain.order.repository.OrderRepository;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;
import org.ssafy.pasila.domain.product.repository.ProductRepository;
import org.ssafy.pasila.domain.sell.dto.OrderManagementDetailDto;
import org.ssafy.pasila.domain.sell.dto.OrderManagementDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SellService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    private final ProductOptionRepository productOptionRepository;

    private final LiveRepository liveRepository;

    public List<OrderManagementDto> getSellProductList(Long sellerId) {

        List<OrderDto> orders = orderRepository.findAllByProductOption_Product_Member_Id(sellerId)
                .stream()
                .map(OrderDto::new)
                .toList();

        return orders.stream()
                .map(order -> {
                    Live live = liveRepository.findByProduct_Id(order.getProductId())
                            .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

                    Member member = memberRepository.findById(order.getSellerId())
                            .orElseThrow(() -> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));

                    List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(order.getProductId())
                            .stream()
                            .map(ProductOptionDto::new)
                            .sorted(Comparator.comparing(ProductOptionDto::getPrice)
                            .thenComparing(ProductOptionDto::getDiscountPrice))
                            .toList();

                    return OrderManagementDto.builder()
                            .id(order.getProductId())
                            .thumbnail(order.getProductUrl())
                            .name(member.getName())
                            .price(options.get(0).getPrice())
                            .discount(options.get(0).getDiscountPrice())
                            .liveOnAt(live.getLiveOnAt())
                            .options(options)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<OrderManagementDetailDto> getSellProductDetail(String productId) {

        return orderRepository.findAllByProductOption_Product_Id(productId)
                .stream()
                .map(order -> OrderManagementDetailDto.builder()
                        .id(order.getId())
                        .buyerName(order.getMember().getName())
                        .address(order.getAddress())
                        .productOptionName(order.getProductOption().getName())
                        .orderCnt(order.getOrderCnt())
                        .status(order.getStatus())
                        .price(order.getPrice())
                        .build())
                .toList();

    }

}