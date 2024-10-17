package io.hhplus.ecommerce.api.cart.application.dto;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.cart.domain.model.CartState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CartResponse (
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "상품명")
        String productName,
        @Schema(description = "장바구니 상품 수량")
        Long quantity,
        @Schema(description = "상품 상태(AVAILABLE: 구매 가능, OUT_OF_STOCK: 품절)")
        CartState state
) {
    public static CartResponse from(Cart cart) {
        return CartResponse.builder()
                .productId(cart.getProduct().getId())
                .productName(cart.getProduct().getProductName())
                .state(cart.getProduct().getStock() == 0 ? CartState.OUT_OF_STOCK : CartState.AVAILABLE)
                .quantity(cart.getQuantity())
                .build();
    }
}
