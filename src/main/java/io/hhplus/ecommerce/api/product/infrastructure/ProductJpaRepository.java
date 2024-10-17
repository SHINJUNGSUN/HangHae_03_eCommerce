package io.hhplus.ecommerce.api.product.infrastructure;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}