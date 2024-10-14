package io.hhplus.ecommerce.api.payment.application;

import lombok.Builder;

public class PaymentDto {
    public record PaymentRequest (
        String userTsid,
        String orderTsid
    ) {}

    @Builder
    public record PaymentResponse (
        String paymentTsid,
        int amount,
        int status
    ) {
        public static PaymentResponse of(String paymentTsid, int amount, int status) {
            return PaymentDto.PaymentResponse.builder()
                    .paymentTsid(paymentTsid)
                    .amount(amount)
                    .status(status)
                    .build();
        }
    }
}
