package io.hhplus.ecommerce.payment.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment payment(long userSeq, Order order) {

        return paymentRepository.save(Payment.ofSuccess(userSeq, order));
    }
}
