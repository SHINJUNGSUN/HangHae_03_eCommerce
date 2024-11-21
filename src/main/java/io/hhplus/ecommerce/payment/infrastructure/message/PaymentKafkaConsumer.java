package io.hhplus.ecommerce.payment.infrastructure.message;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.payment.application.service.PaymentApplicationService;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class PaymentKafkaConsumer {

    private final PaymentApplicationService paymentApplicationService;

    private final CountDownLatch latch = new CountDownLatch(1);

    private String message;

    @RetryableTopic(
            attempts = "1",
            dltStrategy = DltStrategy.ALWAYS_RETRY_ON_ERROR,
            kafkaTemplate = "retryableTopicKafkaTemplate"
    )
    @KafkaListener(
            topics = "payment-complete",
            groupId = "payment-complete-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void paymentComplete(ConsumerRecord<String, String> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Acknowledgment acknowledgment) {

        log.info("[KAFKA] TOPIC: {}, RECORD: {}", topic, record.toString());

        message = record.value();
        latch.countDown();

        paymentApplicationService.sendDataPlatform(ObjectMapperUtil.toObject(record.value(), PaymentCompleteEvent.class));

        acknowledgment.acknowledge();
    }

    @DltHandler
    public void paymentCompleteDlt(ConsumerRecord<String, String> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Acknowledgment acknowledgment) {
        log.info("[KAFKA RETRY] TOPIC: {}, RECORD: {}", topic, record.toString());

        paymentApplicationService.sendDataPlatform(ObjectMapperUtil.toObject(record.value(), PaymentCompleteEvent.class));

        acknowledgment.acknowledge();
    }
}
