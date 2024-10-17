package io.hhplus.ecommerce.api.payment.domain.service;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.payment.domain.model.Payment;
import io.hhplus.ecommerce.api.payment.domain.model.PaymentStatus;

public interface PaymentService {
    Payment payment(long userId, Order order, PaymentStatus paymentStatus);
}
