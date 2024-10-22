package io.hhplus.ecommerce.payment.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.payment.domain.exception.PaymentException;
import io.hhplus.ecommerce.payment.domain.exception.PaymentExceptionType;
import io.hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public PaymentResponse payment(PaymentRequest request) {

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new PaymentException(PaymentExceptionType.ORDER_NOT_FOUND));

        orderLineRepository.findByOrderId(request.orderId()).stream().toList()

        System.out.println(order.getOrderLines().size());

        return null;
    }
}
