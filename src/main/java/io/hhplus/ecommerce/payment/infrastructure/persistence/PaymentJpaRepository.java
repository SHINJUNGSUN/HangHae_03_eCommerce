package io.hhplus.ecommerce.payment.infrastructure.persistence;

import io.hhplus.ecommerce.payment.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {

}
