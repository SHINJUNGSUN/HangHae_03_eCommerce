package io.hhplus.ecommerce.payment.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PaymentRequest (
        @Schema(description = "주문 고유 식별자")
        Long orderId
) {
}
