package io.hhplus.ecommerce.api.payment.domain.repository;

import io.hhplus.ecommerce.api.payment.domain.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
