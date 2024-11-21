package io.hhplus.ecommerce.payment;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import io.hhplus.ecommerce.payment.infrastructure.message.PaymentKafkaConsumer;
import io.hhplus.ecommerce.payment.infrastructure.message.PaymentKafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"payment-complete"})
public class PaymentEmbeddedKafkaTest {

    @Autowired
    PaymentKafkaProducer paymentKafkaProducer;

    @Autowired
    PaymentKafkaConsumer paymentKafkaConsumer;

    @Test
    @DisplayName("")
    void embeddedKafkaTest_paymentComplete() throws InterruptedException {
        // Given
        long userSeq = 1L;
        long orderId = 1L;
        PaymentCompleteEvent event = PaymentCompleteEvent.of(userSeq, orderId);

        // When
        paymentKafkaProducer.paymentComplete(event);

        // Then
        boolean messageConsumed = paymentKafkaConsumer.getLatch().await(2, TimeUnit.SECONDS);
        assertTrue(messageConsumed);
        assertEquals(paymentKafkaConsumer.getMessage(), ObjectMapperUtil.toJson(event));
    }
}
