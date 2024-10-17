package io.hhplus.ecommerce.api.product.domain.repository;

import io.hhplus.ecommerce.api.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByProductId(long productId);
    List<Product> findAll();
    Product save(Product product);
}
