package io.hhplus.ecommerce.product.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductExceptionType {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, 400001, "상품이 존재하지 않습니다."),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 400002, "올바르지 않은 수량입니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, 400003, "상품 재고가 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
