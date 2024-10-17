package io.hhplus.ecommerce.api.payment.domain.service;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.payment.domain.model.Payment;
import io.hhplus.ecommerce.api.payment.domain.model.PaymentStatus;
import io.hhplus.ecommerce.api.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentDomainService implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment payment(long userId, Order order, PaymentStatus paymentStatus) {

        return paymentRepository.save(Payment.create(userId, order, paymentStatus));
    }
}
