package io.hhplus.ecommerce.payment.domain.exception;

import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException {

    private final PaymentExceptionType exception;

    public PaymentException(PaymentExceptionType exception) {
        this.exception = exception;
    }
}
