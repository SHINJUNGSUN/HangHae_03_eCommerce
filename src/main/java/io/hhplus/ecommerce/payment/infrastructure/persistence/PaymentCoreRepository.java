package io.hhplus.ecommerce.payment.infrastructure.persistence;

import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import io.hhplus.ecommerce.payment.infrastructure.persistence.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentCoreRepository implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentJpaRepository.save(PaymentEntity.of(payment)).toDomain();
    }
}
