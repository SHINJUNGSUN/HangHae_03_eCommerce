package io.hhplus.ecommerce.payment.application.handler;

import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import io.hhplus.ecommerce.payment.infrastructure.message.PaymentKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {

    private final PaymentKafkaProducer paymentKafkaProducer;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentCompleteEventHandler(PaymentCompleteEvent event) {
        paymentKafkaProducer.paymentComplete(event);
    }
}
