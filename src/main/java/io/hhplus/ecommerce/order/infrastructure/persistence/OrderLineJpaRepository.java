package io.hhplus.ecommerce.order.infrastructure.persistence;

import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderLineEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderLineJpaRepository extends JpaRepository<OrderLineEntity, Long> {

    @Query("""
        SELECT ol.productId
        FROM OrderLineEntity ol
        WHERE ol.createdAt BETWEEN :startDateTime AND :endDateTime
        GROUP BY ol.productId
        ORDER BY SUM(ol.quantity) DESC
    """)
    List<Long> findPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);
    List<OrderLineEntity> findByOrderId(Long orderId);
    List<OrderLineEntity> findByProductId(Long productId);
}
