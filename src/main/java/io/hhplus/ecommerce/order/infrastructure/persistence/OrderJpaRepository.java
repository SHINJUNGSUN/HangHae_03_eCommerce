package io.hhplus.ecommerce.order.infrastructure.persistence;

import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByIdAndOrderStatus(long orderId, OrderStatus orderStatus);
}
