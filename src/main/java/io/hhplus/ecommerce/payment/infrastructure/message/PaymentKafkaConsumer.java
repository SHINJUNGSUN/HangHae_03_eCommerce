package io.hhplus.ecommerce.payment.infrastructure.message;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.payment.application.service.PaymentApplicationService;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentKafkaConsumer {

    private final PaymentApplicationService paymentApplicationService;

    private CountDownLatch latch = new CountDownLatch(1);

    private String message;

    @KafkaListener(topics = "payment-complete", groupId = "payment-complete-group")
    public void paymentComplete(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {

        log.info("[KAFKA] RECORD: {}", record.toString());

        message = record.value();
        latch.countDown();

        paymentApplicationService.sendDataPlatform(ObjectMapperUtil.toObject(record.value(), PaymentCompleteEvent.class));

        acknowledgment.acknowledge();
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public String getMessage() {
        return this.message;
    }
}
