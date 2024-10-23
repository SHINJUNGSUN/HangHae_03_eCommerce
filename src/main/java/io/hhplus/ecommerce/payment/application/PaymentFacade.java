package io.hhplus.ecommerce.payment.application;

import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.payment.application.service.PaymentService;
import io.hhplus.ecommerce.payment.domain.exception.PaymentException;
import io.hhplus.ecommerce.payment.domain.exception.PaymentExceptionType;
import io.hhplus.ecommerce.user.application.service.UserPointService;
import io.hhplus.ecommerce.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final OrderService orderService;
    private final UserPointService userPointService;
    private final PaymentService paymentService;

    @Transactional
    public PaymentResponse payment(PaymentRequest request) {

        Order order = orderService.getOrder(request.orderId())
                .orElseThrow(() -> new PaymentException(PaymentExceptionType.ORDER_NOT_FOUND));

        if(order.getOrderStatus() == OrderStatus.CANCELED) {
            throw new PaymentException(PaymentExceptionType.CANCELED_ORDER);
        }

        if(order.getOrderStatus() == OrderStatus.COMPLETED) {
            throw new PaymentException(PaymentExceptionType.COMPLETED_ORDER);
        }

        User user = userPointService.getPoint(request.userId());

        if(order.totalPrice() > user.getUserPoint().getPoint()) {
            throw new PaymentException(PaymentExceptionType.ORDER_NOT_FOUND);
        }

        userPointService.usePoint(request.userId(), order.totalPrice());

        orderService.completeOrder(order);

        return PaymentResponse.from(paymentService.payment(request.userId(), order));
    }
}
