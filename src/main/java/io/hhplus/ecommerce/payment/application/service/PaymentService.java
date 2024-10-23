package io.hhplus.ecommerce.payment.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.payment.domain.model.Payment;

public interface PaymentService {
    Payment payment(long userSeq, Order order);
}
