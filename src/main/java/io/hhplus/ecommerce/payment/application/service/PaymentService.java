package io.hhplus.ecommerce.payment.application.service;

import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse payment(PaymentRequest request);
}
