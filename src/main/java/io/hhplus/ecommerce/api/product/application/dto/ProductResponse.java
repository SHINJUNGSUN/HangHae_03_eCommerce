package io.hhplus.ecommerce.api.product.application.dto;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record ProductResponse(
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
        return ProductResponse.builder()
                .productId(product.getId())
                .ProductName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .stock(product.getStock())
                .build();
    }
}
