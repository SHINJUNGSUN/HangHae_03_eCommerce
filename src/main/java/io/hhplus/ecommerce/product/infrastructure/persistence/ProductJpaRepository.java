package io.hhplus.ecommerce.product.infrastructure.persistence;

import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<ProductEntity> findById(long id);
}
