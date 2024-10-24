package io.hhplus.ecommerce.payment.application.dto;

import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.payment.domain.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record PaymentResponse (
        @Schema(description = "결제 고유 식별자")
        Long paymentId,
        @Schema(description = "결제 금액")
        Long amount,
        @Schema(description = "결제 상태 (SUCCESS: 결제 성공, FAILED: 결제 실패, CANCELLED: 결제 취소)")
        PaymentStatus paymentStatus
) {
    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getPaymentId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
