package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.enums.ProductError;
import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductError error;

    public ProductException(ProductError error) {
        this.error = error;
    }
}
