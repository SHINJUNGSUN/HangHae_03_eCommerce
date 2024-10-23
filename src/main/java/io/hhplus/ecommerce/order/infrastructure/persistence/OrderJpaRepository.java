package io.hhplus.ecommerce.order.infrastructure.persistence;

import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {

}
