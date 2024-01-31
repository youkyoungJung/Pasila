package org.ssafy.pasila.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssafy.pasila.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
