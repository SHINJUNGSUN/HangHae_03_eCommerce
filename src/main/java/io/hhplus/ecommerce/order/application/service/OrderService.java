package io.hhplus.ecommerce.order.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrder(long orderId, OrderStatus orderStatus);
    Order createOrder(long userSeq, List<OrderLine> orderLines);
    void updateOrderStatus(OrderStatus orderStatus, Order order);
    List<Long> getPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
