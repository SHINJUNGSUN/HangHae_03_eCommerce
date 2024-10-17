package io.hhplus.ecommerce.api.cart.application.dto;

public record CartAddRequest(
        Long userId,
        Long productId,
        Long quantity
) {
}
