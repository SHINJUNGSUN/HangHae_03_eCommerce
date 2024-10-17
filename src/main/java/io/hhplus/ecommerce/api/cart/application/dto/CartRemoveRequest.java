package io.hhplus.ecommerce.api.cart.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CartRemoveRequest(
        @Schema(description = "사용자 고유 식별자")
        Long userId,
        @Schema(description = "상품 고유 식별자")
        Long productId
) {
}
