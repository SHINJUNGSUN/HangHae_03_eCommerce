package io.hhplus.ecommerce.api.order.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderProduct (
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "주문 상품 수량")
        Long quantity
) {
}
