package org.ssafy.pasila.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.order.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByMemberId(Long id);
}
