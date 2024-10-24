package io.hhplus.ecommerce.cart.application.dto;

import io.hhplus.ecommerce.cart.domain.model.CartProduct;
import io.hhplus.ecommerce.cart.domain.model.CartProductState;
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
        @Schema(description = "장바구니 상품 상태 (AVAILABLE: 구매 가능, OUT_OF_STOCK: 재고 부족, NOT_AVAILABLE: 구매 불가)")
        CartProductState cartProductState
) {
    public static CartResponse of(CartProduct cartProduct) {
        return new CartResponse(
                cartProduct.getCart().getProductId(),
                cartProduct.getProduct().getProductName(),
                cartProduct.getProduct().getUnitPrice(),
                cartProduct.getCart().getQuantity(),
                cartProduct.getCartProductState()
        );
    }
}
