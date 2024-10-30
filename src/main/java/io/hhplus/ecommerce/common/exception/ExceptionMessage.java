package io.hhplus.ecommerce.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionMessage {
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),
    ALREADY_SIGNED_UP("이미 가입한 사용자입니다."),
    INVALID_AMOUNT("유효하지 않은 포인트입니다."),
    INSUFFICIENT_POINT("사용자 포인트가 부족합니다."),
    PRODUCT_NOT_FOUND("상품이 존재하지 않습니다."),
    CART_NOT_FOUND("장바구니 상품이 존재하지 않습니다."),
    INVALID_QUANTITY("유효하지 않은 수량입니다."),
    INSUFFICIENT_STOCK("상품 재고가 부족합니다."),
    ORDER_NOT_FOUND("존재하지 않는 주문입니다."),
    PRODUCT_REDUCE_FAILED("상품 재고 감소에 실패하였습니다."),
    REDIS_LOCK_ACQUIRE_FAILED("레디스 락 획득에 실패하였습니다. ");

    String message;
}
