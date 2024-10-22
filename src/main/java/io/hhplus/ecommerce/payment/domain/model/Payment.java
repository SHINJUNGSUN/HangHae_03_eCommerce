package io.hhplus.ecommerce.payment.domain.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    private Long paymentId;

    private Long userSeq;

    private Long orderId;

    private Long amount;

    private PaymentStatus paymentStatus;

    public static Payment create(long userSeq, long orderId, long amount, PaymentStatus paymentStatus) {
        return Payment.builder()
                .userSeq(userSeq)
                .orderId(orderId)
                .amount(amount)
                .paymentStatus(paymentStatus)
                .build();
    }
}
