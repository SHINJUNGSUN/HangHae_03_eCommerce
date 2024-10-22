package io.hhplus.ecommerce.payment.infrastructure.persistence.entity;

import io.hhplus.ecommerce.common.model.TimeStamped;
import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.payment.domain.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userSeq;

    private Long orderId;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public static PaymentEntity of(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getPaymentId())
                .userSeq(payment.getUserSeq())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }

    public Payment toDomain() {
        return Payment.builder()
                .paymentId(this.id)
                .userSeq(this.userSeq)
                .orderId(this.orderId)
                .amount(this.amount)
                .paymentStatus(this.paymentStatus)
                .build();
    }
}
