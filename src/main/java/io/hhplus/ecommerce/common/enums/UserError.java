package io.hhplus.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 100001, "존재하지 않는 사용자"),
    INVALID_CHARGE_AMOUNT(HttpStatus.BAD_REQUEST, 100002, "유효하지 않은 포인트 충전 금액"),
    INVALID_USE_AMOUNT(HttpStatus.BAD_REQUEST, 100003, "유효하지 않은 포인트 사용 금액"),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, 100004, "포인트 잔액 부족");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}