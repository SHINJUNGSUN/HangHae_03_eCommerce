package io.hhplus.ecommerce.api.payment.infrastructure;

import io.hhplus.ecommerce.api.payment.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
