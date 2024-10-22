package io.hhplus.ecommerce.cart.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CartExceptionType {
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, 300001, "장바구니 상품이 존재하지 않습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, 300002, "상품이 존재하지 않습니다."),
    INVALID_AMOUNT(HttpStatus.BAD_REQUEST, 300003, "유효하지 않은 장바구니 상품 수량입니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, 300004, "상품 재고가 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
