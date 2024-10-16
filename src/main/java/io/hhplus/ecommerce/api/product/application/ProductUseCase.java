package io.hhplus.ecommerce.api.product.application;

import io.hhplus.ecommerce.api.product.application.dto.ProductResponse;

import java.util.List;

public interface ProductUseCase {
    List<ProductResponse> products();
    List<ProductResponse> popularProducts();
}
