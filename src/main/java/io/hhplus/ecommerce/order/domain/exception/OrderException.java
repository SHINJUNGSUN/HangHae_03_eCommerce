package io.hhplus.ecommerce.order.domain.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final OrderExceptionType exception;

    public OrderException(OrderExceptionType exception) {
        this.exception = exception;
    }
}
