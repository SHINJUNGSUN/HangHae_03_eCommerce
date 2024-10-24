package io.hhplus.ecommerce.product.domain.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductExceptionType exception;

    public ProductException(ProductExceptionType exception) {
        this.exception = exception;
    }
}
