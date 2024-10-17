package io.hhplus.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductError {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, 200001, "존재하지 않는 상품입니다."),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 200002, "유효하지 않은 상품 수량입니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, 200003, "상품 재고가 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
