package io.hhplus.ecommerce.product.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductRequest (
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "상품 수량")
        Long amount
) {
}
