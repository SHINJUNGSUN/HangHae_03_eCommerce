package io.hhplus.ecommerce.api.product.domain.repository;

import io.hhplus.ecommerce.api.product.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product findByProductId(long productId);
    List<Product> findAll();
}
