package io.hhplus.ecommerce.api.order.infrastructure;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.api.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderCoreRepository implements OrderRepository, OrderLineRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderLineJpaRepository orderLineJpaRepository;

    @Override
    public Order save(Order order) {
        return orderJpaRepository.save(order);
    }

    @Override
    public List<Long> popularProducts(LocalDateTime dateTime) {
        return orderLineJpaRepository.findPopularProducts(dateTime);
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return orderJpaRepository.findById(orderId);
    }
}
