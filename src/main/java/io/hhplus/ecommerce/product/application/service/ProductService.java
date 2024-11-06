package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.product.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(long productId);
    List<Product> getProducts();
    Product reduceProduct(long productId, long quantity);
    void saveProducts(long count);
}
