package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.model.Products;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(long productId);
    Products getProducts();
    Product reduceProduct(long productId, long quantity);
    void saveProducts(long count);
}
