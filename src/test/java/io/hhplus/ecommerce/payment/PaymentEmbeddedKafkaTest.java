package io.hhplus.ecommerce.payment;

import io.hhplus.ecommerce.common.util.ObjectMapperUtil;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import io.hhplus.ecommerce.payment.infrastructure.message.PaymentKafkaConsumer;
import io.hhplus.ecommerce.payment.infrastructure.message.PaymentKafkaProducer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"payment-complete"})
public class PaymentEmbeddedKafkaTest {

    @SpyBean
    PaymentKafkaProducer paymentKafkaProducer;

    @SpyBean
    PaymentKafkaConsumer paymentKafkaConsumer;

    @Test
    @DisplayName("결제 완료 이벤트 카프카 테스트")
    void embeddedKafkaTest_paymentComplete() throws InterruptedException {
        // Given
        long userSeq = 1L;
        long orderId = 1L;
        PaymentCompleteEvent event = PaymentCompleteEvent.of(userSeq, orderId);

        // When
        paymentKafkaProducer.paymentComplete(event);

        // Then
        assertThat(paymentKafkaConsumer.getLatch().await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(paymentKafkaConsumer.getMessage()).isEqualTo(ObjectMapperUtil.toJson(event));
    }

    @Test
    @DisplayName("Consume 로직이 성공한 경우, DLT 에 게시되지 않음")
    void embeddedKafkaTest_DLT_success() throws InterruptedException {
        // Given
        long userSeq = 1L;
        long orderId = 1L;
        PaymentCompleteEvent event = PaymentCompleteEvent.of(userSeq, orderId);

        CountDownLatch mainTopicCountDownLatch = new CountDownLatch(1);

        doAnswer(invocation -> {
            mainTopicCountDownLatch.countDown();
            return null;
        }).when(paymentKafkaConsumer)
                .paymentComplete(any(), any(), any());

        // When
        paymentKafkaProducer.paymentComplete(event);

        // Then
        assertThat(mainTopicCountDownLatch.await(10, TimeUnit.SECONDS)).isTrue();
        verify(paymentKafkaConsumer, never()).paymentCompleteDlt(any(), any(), any());
    }

    @Test
    @DisplayName("Consume 로직이 실패한 경우, DLT 에 의해 재시도 됨")
    void embeddedKafkaTest_DLT_failed() throws InterruptedException {
        // Given
        long userSeq = 1L;
        long orderId = 1L;
        PaymentCompleteEvent event = PaymentCompleteEvent.of(userSeq, orderId);

        CountDownLatch mainTopicCountDownLatch = new CountDownLatch(1);
        CountDownLatch dltTopicCountDownLatch = new CountDownLatch(2);

        doAnswer(invocation -> {
            mainTopicCountDownLatch.countDown();
            throw new RuntimeException();
        }).when(paymentKafkaConsumer)
                .paymentComplete(any(), any(), any());

        doAnswer(invocation -> {
            dltTopicCountDownLatch.countDown();
            throw new RuntimeException();
        }).when(paymentKafkaConsumer)
                .paymentCompleteDlt(any(), any(), any());

        // When
        paymentKafkaProducer.paymentComplete(event);

        // Then
        assertThat(mainTopicCountDownLatch.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(dltTopicCountDownLatch.await(10, TimeUnit.SECONDS)).isFalse();
        assertThat(dltTopicCountDownLatch.getCount()).isEqualTo(1);
    }
}
