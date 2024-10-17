package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.enums.CartError;
import lombok.Getter;

@Getter
public class CartException extends RuntimeException {

    private final CartError error;

    public CartException(CartError error) {
        this.error = error;
    }
}
