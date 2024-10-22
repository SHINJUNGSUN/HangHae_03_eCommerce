package io.hhplus.ecommerce.product.infrastructure.persistence;

import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
