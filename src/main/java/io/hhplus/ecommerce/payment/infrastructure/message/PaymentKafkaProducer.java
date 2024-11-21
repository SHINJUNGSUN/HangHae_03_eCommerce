package io.hhplus.ecommerce.payment.infrastructure.message;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void paymentComplete(PaymentCompleteEvent event) {
        kafkaTemplate.send("payment-complete", ObjectMapperUtil.toJson(event));
    }
}
