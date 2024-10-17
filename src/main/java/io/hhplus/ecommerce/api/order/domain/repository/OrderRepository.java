package io.hhplus.ecommerce.api.order.domain.repository;

import io.hhplus.ecommerce.api.order.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(long orderId);
}
