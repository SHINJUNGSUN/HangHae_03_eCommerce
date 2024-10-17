package io.hhplus.ecommerce.api.product.domain.service;

import io.hhplus.ecommerce.api.product.domain.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(long productId);
    List<Product> getProducts();
}
