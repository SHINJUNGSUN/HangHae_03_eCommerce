package io.hhplus.ecommerce.payment.domain.event;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentCompleteEvent {

    private Long userSeq;

    private Long orderId;

    public static PaymentCompleteEvent of(Long userSeq, Long orderId) {
        return PaymentCompleteEvent.builder()
                .userSeq(userSeq)
                .orderId(orderId)
                .build();
    }
}
