package io.hhplus.ecommerce.payment.domain.model;

import io.hhplus.ecommerce.order.domain.model.Order;
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

    public static Payment ofSuccess(long userSeq, Order order) {
        return Payment.builder()
                .userSeq(userSeq)
                .orderId(order.getOrderId())
                .amount(order.totalPrice())
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();
    }
}
