package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.enums.OrderError;
import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final OrderError error;

    public OrderException(OrderError error) {
        this.error = error;
    }
}
