package io.hhplus.ecommerce.order.domain.repository;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(long orderId);
    void deleteAll();
    OrderLine save(OrderLine orderLine);
    List<OrderLine> findByOrderId(Long orderId);
    List<Long> findPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
