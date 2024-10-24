package io.hhplus.ecommerce.order.infrastructure.persistence;

import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OrderEntity> findByIdAndOrderStatus(long orderId, OrderStatus orderStatus);
}
