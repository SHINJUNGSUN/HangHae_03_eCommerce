package io.hhplus.ecommerce.api.payment.domain.model;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long orderId;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;

    public static Payment create(long userId, Order order, PaymentStatus paymentStatus) {
        return Payment.builder()
                .userId(userId)
                .orderId(order.getId())
                .amount(order.totalPrice())
                .paymentStatus(paymentStatus)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
