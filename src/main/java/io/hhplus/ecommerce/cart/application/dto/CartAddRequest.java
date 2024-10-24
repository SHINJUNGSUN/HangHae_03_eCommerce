package io.hhplus.ecommerce.cart.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CartAddRequest(
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "장바구니 상품 추가 수량")
        Long quantity
) {
}
