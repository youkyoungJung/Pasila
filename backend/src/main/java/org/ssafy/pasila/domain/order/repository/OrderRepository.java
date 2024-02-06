package org.ssafy.pasila.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssafy.pasila.domain.order.dto.OrderDto;
import org.ssafy.pasila.domain.order.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByMemberId(Long id);

    List<Order> findAllByProductOption_Product_Id(String id);

    @Query("Select new org.ssafy.pasila.domain.order.dto.OrderDto" +
            "(o.id, o.orderCnt, o.name, o.member.name, o.address, o.price, o.createdAt, o.status," +
            " o.productOption.id, o.productOption.name, o.productOption.product.id, o.productOption.product.name, o.productOption.product.thumbnail, o.productOption.product.member.id) " +
            "from Order o " +
            "where o.id = :id")
    Optional<OrderDto> findOrderDtoById(Long id);

}