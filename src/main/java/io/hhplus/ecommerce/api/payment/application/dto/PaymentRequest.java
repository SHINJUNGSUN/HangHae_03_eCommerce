package io.hhplus.ecommerce.api.payment.application.dto;

public record PaymentRequest (
        Long userId,
        Long orderId
) {
}
