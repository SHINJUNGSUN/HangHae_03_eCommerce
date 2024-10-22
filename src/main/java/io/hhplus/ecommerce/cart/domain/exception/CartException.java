package io.hhplus.ecommerce.cart.domain.exception;

import lombok.Getter;

@Getter
public class CartException extends RuntimeException {

    private final CartExceptionType exception;

    public CartException(CartExceptionType exception) {
        this.exception = exception;
    }
}
