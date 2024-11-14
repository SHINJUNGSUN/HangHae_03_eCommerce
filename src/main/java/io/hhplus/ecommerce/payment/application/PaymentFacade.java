package io.hhplus.ecommerce.payment.application;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.common.util.SlackMessageUtil;
import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.payment.application.service.PaymentService;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.user.application.service.UserPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final ApplicationEventPublisher eventPublisher;

    private final SlackMessageUtil slackMessageUtil;

    private final OrderService orderService;

    private final UserPointService userPointService;

    private final PaymentService paymentService;

    @Transactional
    public PaymentResponse payment(long userSeq, PaymentRequest request) {

        Order order = orderService.getOrder(request.orderId(), OrderStatus.PENDING)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.ORDER_NOT_FOUND.getMessage()));

        userPointService.usePoint(userSeq, order.totalPrice());

        orderService.updateOrderStatus(OrderStatus.COMPLETED, order);

        Payment payment = paymentService.payment(userSeq, order);

        eventPublisher.publishEvent(PaymentCompleteEvent.of(userSeq, order));

        return PaymentResponse.from(payment);
    }
}
