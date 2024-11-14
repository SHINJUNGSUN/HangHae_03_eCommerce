package io.hhplus.ecommerce.payment.domain.event;

import io.hhplus.ecommerce.order.domain.model.Order;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentCompleteEvent {
    private Long userSeq;
    private Order order;

    public static PaymentCompleteEvent of(Long userSeq, Order order) {
        return PaymentCompleteEvent.builder()
                .userSeq(userSeq)
                .order(order)
                .build();
    }
}
