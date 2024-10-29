package io.hhplus.ecommerce.order.domain.repository;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findByIdAndOrderStatus(long orderId, OrderStatus orderStatus);
    OrderLine save(OrderLine orderLine);
    List<OrderLine> findByOrderId(Long orderId);
    List<Long> findPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<OrderLine> findByProductId(Long productId);
}
