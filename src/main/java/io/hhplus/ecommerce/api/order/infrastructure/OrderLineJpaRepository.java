package io.hhplus.ecommerce.api.order.infrastructure;

import io.hhplus.ecommerce.api.order.domain.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderLineJpaRepository extends JpaRepository<OrderLine, Long> {

    @Query("SELECT ol.productId FROM OrderLine ol WHERE ol.createdAt >= :dateTime GROUP BY ol.productId ORDER BY SUM(ol.quantity) DESC LIMIT 5")
    List<Long> findPopularProducts(LocalDateTime dateTime);
}
