package io.hhplus.ecommerce.product.application.dto;

import io.hhplus.ecommerce.product.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProductResponse (
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "상품명")
        String ProductName,
        @Schema(description = "상품 단가")
        Long unitPrice,
        @Schema(description = "재고")
        Long stock
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getUnitPrice(),
                product.getStock()
        );
    }
}
