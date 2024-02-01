package org.ssafy.pasila.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.pasila.domain.order.dto.OrderDto;
import org.ssafy.pasila.domain.order.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByMemberId(Long id);

    @Query("Select new org.ssafy.pasila.domain.order.dto.OrderDto" +
            "(o.id, o.orderCnt, o.name, o.address, o.price, o.createdAt, o.status," +
            " o.productOption.id, o.productOption.name, o.productOption.product.name, o.productOption.product.thumbnail) " +
            "from Order o " +
            "where o.id = :id")
    Optional<OrderDto> findOrderDtoById(Long id);
}
