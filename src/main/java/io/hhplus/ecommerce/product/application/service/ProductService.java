package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.product.application.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts();
    List<ProductResponse> getPopularProducts();
}
