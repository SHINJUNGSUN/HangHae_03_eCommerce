package io.hhplus.ecommerce.order.domain.repository;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(long orderId);
    void deleteAll();
}
