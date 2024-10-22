package io.hhplus.ecommerce.order.domain.repository;

import io.hhplus.ecommerce.order.domain.model.OrderLine;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderLineRepository {
    OrderLine save(OrderLine orderLine);
    List<OrderLine> findByOrderId(Long orderId);
    List<Long> findPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
