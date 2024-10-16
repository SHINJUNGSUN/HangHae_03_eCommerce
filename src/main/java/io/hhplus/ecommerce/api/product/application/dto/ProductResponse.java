package io.hhplus.ecommerce.api.product.application.dto;

import io.hhplus.ecommerce.api.product.domain.Product;
import lombok.Builder;

@Builder
public record ProductResponse(
        Long productId,
        String ProductName,
        Long unitPrice,
        Long stock
) {
    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .ProductName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .stock(product.getStock())
                .build();
    }
}
