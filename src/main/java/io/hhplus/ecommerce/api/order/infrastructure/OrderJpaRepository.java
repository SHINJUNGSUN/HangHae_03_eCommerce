package io.hhplus.ecommerce.api.order.infrastructure;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
