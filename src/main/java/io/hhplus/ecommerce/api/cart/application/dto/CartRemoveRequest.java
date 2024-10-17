package io.hhplus.ecommerce.api.cart.application.dto;

public record CartRemoveRequest(
        Long userId,
        Long productId
) {
}
