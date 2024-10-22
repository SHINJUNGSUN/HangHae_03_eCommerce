package io.hhplus.ecommerce.order.infrastructure.persistence;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import io.hhplus.ecommerce.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderLineEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return orderJpaRepository.save(OrderEntity.of(order)).toDomain();
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return orderLineJpaRepository.save(OrderLineEntity.of(orderLine)).toDomain();
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return orderJpaRepository.findById(orderId).map(OrderEntity::toDomain);
    }

    @Override
    public List<OrderLine> findByOrderId(Long orderId) {
        return orderLineJpaRepository.findByOrderId(orderId)
                .stream()
                .map(OrderLineEntity::toDomain)
                .toList();
    }

    @Override
    public void deleteAll() {
        orderJpaRepository.deleteAll();
    }

    @Override
    public List<Long> findPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Pageable pageable = PageRequest.of(0, 5);
        return orderLineJpaRepository.findPopularProducts(startDateTime, endDateTime, pageable);
    }
}
