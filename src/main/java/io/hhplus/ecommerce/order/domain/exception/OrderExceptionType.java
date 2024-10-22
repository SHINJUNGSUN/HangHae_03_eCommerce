package io.hhplus.ecommerce.order.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum OrderExceptionType {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, 400001, "존재하지 않는 주문입니다."),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, 400002, "존재하지 않는 상품입니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, 400003, "상품 재고가 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
