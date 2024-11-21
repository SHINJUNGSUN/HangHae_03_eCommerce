package io.hhplus.ecommerce.payment.application.service;

import io.hhplus.ecommerce.common.util.SlackMessageUtil;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import io.hhplus.ecommerce.payment.domain.model.Payment;
import io.hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentApplicationService implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final SlackMessageUtil slackMessageUtil;

    @Override
    public Payment payment(long userSeq, Order order) {

        return paymentRepository.save(Payment.ofSuccess(userSeq, order));
    }

    @Override
    public void sendDataPlatform(PaymentCompleteEvent event) {

        slackMessageUtil.sendMessage(String.format("사용자(사용자 고유 식별자: %d)가 주문(주문번호: %d)에 대한 결제를 완료하였습니다.", event.getUserSeq(), event.getOrderId()));
    }
}
