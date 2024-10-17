package io.hhplus.ecommerce.api.payment.application.dto;

import io.hhplus.ecommerce.api.payment.domain.model.Payment;
import io.hhplus.ecommerce.api.payment.domain.model.PaymentStatus;
import lombok.Builder;

@Builder
public record PaymentResponse (
        Long paymentId,
        Long amount,
        PaymentStatus paymentStatus
) {
    public static PaymentResponse from(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}
