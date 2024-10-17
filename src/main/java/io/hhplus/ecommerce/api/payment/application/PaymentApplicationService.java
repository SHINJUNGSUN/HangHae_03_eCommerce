package io.hhplus.ecommerce.api.payment.application;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.service.OrderService;
import io.hhplus.ecommerce.api.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.api.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.api.payment.domain.model.Payment;
import io.hhplus.ecommerce.api.payment.domain.model.PaymentStatus;
import io.hhplus.ecommerce.api.payment.domain.service.PaymentService;
import io.hhplus.ecommerce.api.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final UserService userService;

    @Transactional
    public PaymentResponse payment(PaymentRequest request) {

        Order order = orderService.order(request.orderId());

        Payment payment;

        try {
            userService.usePoint(request.userId(), order.totalPrice());
            payment = paymentService.payment(request.userId(), order, PaymentStatus.SUCCESS);
            orderService.completeOrder(order);
        } catch (Exception e) {
            payment = paymentService.payment(request.userId(), order, PaymentStatus.FAILED);
        }

        return PaymentResponse.from(payment);
    }
}
