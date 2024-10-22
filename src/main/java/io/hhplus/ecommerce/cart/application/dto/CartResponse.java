package io.hhplus.ecommerce.cart.application.dto;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.model.CartState;
import io.swagger.v3.oas.annotations.media.Schema;

public record CartResponse (
        @Schema(description = "상품 고유 식별자")
        Long productId,
        @Schema(description = "상품명")
        String productName,
        @Schema(description = "장바구니 상품 단가")
        Long unitPrice,
        @Schema(description = "장바구니 상품 수량")
        Long quantity,
        @Schema(description = "장바구니 상품 상태 (AVAILABLE: 구매 가능, OUT_OF_STOCK: 재고 부족)")
        CartState cartState
) {
    public static CartResponse from(Cart cart) {
        return new CartResponse(
                cart.getProduct().getProductId(),
                cart.getProduct().getProductName(),
                cart.getProduct().getUnitPrice(),
                cart.getQuantity(),
                cart.getProduct().getStock() > cart.getQuantity() ? CartState.AVAILABLE : CartState.OUT_OF_STOCK
        );
    }
}
