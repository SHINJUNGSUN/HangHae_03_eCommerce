package io.hhplus.ecommerce.product.infrastructure.persistence;

import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(long id);
}
