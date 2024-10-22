package io.hhplus.ecommerce.cart.infrastructure.persistence;

import io.hhplus.ecommerce.cart.infrastructure.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByUserSeq(long userSeq);
    Optional<CartEntity> findByUserSeqAndProductId(Long userSeq, Long productId);
}
