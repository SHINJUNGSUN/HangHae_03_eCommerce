package io.hhplus.ecommerce.payment.application.eventListener;

import io.hhplus.ecommerce.common.util.SlackMessageUtil;
import io.hhplus.ecommerce.payment.domain.event.PaymentCompleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PaymentEventHandler {

    private final SlackMessageUtil slackMessageUtil;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void paymentCompleteEventHandler(PaymentCompleteEvent event) {
        String message = String.format("사용자(UserSeq: %d) 결제 성공!", event.getUserSeq());
        slackMessageUtil.sendMessage(message);
    }
}
