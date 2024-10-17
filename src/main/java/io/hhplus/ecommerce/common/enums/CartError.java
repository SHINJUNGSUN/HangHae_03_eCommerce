package io.hhplus.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CartError {
    CART_NOT_FOUNT(HttpStatus.NOT_FOUND, 300001, "존재하지 않는 장바구니 상품"),
    INVALID_QUANTITY(HttpStatus.BAD_REQUEST, 300002, "유효하지 않은 장바구니 상품 수량");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
