package io.hhplus.ecommerce.cart.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CartRemoveRequest(
        @Schema(description = "상품 고유 식별자")
        Long productId
) {
}
