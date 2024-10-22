package io.hhplus.ecommerce.payment.domain.repository;


import io.hhplus.ecommerce.payment.domain.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
