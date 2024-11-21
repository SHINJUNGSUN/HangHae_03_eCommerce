package io.hhplus.ecommerce.payment.infrastructure.message;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.common.util.SlackMessageUtil;
import io.hhplus.ecommerce.payment.application.service.PaymentApplicationService;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentKafkaConsumer {

    private final PaymentApplicationService paymentApplicationService;

    @KafkaListener(topics = "payment-complete", groupId = "payment-complete-group")
    public void paymentComplete(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        log.info("[KAFKA] {}", record.toString());
        paymentApplicationService.sendDataPlatform(ObjectMapperUtil.toObject(record.value(), PaymentCompleteEvent.class));
        acknowledgment.acknowledge();
    }
}
