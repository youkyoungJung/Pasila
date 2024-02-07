package org.ssafy.pasila.domain.order.service;

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
import org.ssafy.pasila.domain.order.dto.OrderFormDto;
import org.ssafy.pasila.domain.order.entity.Order;
import org.ssafy.pasila.domain.order.entity.Status;
import org.ssafy.pasila.domain.order.repository.OrderRepository;
import org.ssafy.pasila.domain.product.dto.ProductOptionDto;
import org.ssafy.pasila.domain.product.entity.ProductOption;
import org.ssafy.pasila.domain.product.repository.ProductOptionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ProductOptionRepository productOptionRepository;

    private final LiveRepository liveRepository;

    private final SseEmitterService sseEmitterService;

    /** 주문 생성 */
    @Transactional
    public List<Long> saveOrder(OrderFormDto orderformDto){

        List<Long> list = new ArrayList<>();
        List<Long> optionsId = orderformDto.getOptions();

        Member member = memberRepository.findById(orderformDto.getMemberId())
                    .orElseThrow(()-> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));

        String productId = null;

        for(Long optionId : optionsId){

            ProductOption productOption = productOptionRepository.findById(optionId)
                    .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

            productId = productOption.getProduct().getId();
            //주문 생성
            Order order = Order.createOrder(orderformDto, member, productOption);
            orderRepository.save(order);
            list.add(order.getId());
        }


        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(productId)
        .stream()
        .map(option -> ProductOptionDto.builder()
                .id(option.getId())
                .name(option.getName())
                .stock(option.getStock())
                .price(option.getPrice())
                .discountPrice(option.getDiscountPrice())
                .build()).toList();

        Optional<Live> live = liveRepository.findByProduct_IdAndIsOnTrue(productId);
        live.ifPresent(l -> sseEmitterService.send(l.getId(), options));

        return list;

    }

    public List<OrderDto> getOrderList(Long id){

        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST));

        List<Order> orders = orderRepository.findAllByMemberId(member.getId());
        return orders.stream()
                .map(OrderDto::new)
                .collect(toList());

    }

    /**
     * id : OrderId
     * */
    public OrderDto getOrderDetail(Long id){

        return orderRepository.findOrderDtoById(id)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

    }

    @Transactional
    public Long cancelOrder(Long id){

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        order.cancel();

        List<ProductOptionDto> options = productOptionRepository.findAllByProduct_Id(order.getProductOption().getProduct().getId())
                .stream()
                .map(option -> ProductOptionDto.builder()
                        .id(option.getId())
                        .name(option.getName())
                        .stock(option.getStock())
                        .price(option.getPrice())
                        .discountPrice(option.getDiscountPrice())
                        .build()).toList();

        Optional<Live> live = liveRepository.findByProduct_IdAndIsOnTrue(order.getProductOption().getProduct().getId());
        live.ifPresent(l -> sseEmitterService.send(l.getId(), options));

        return order.getId();

    }

    @Transactional
    public Long changeStatus(Long id, String status){

        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        order.changeStatus(Enum.valueOf(Status.class, status));
        return order.getId();

    }

}