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
    INVALID_CHARGE_AMOUNT(HttpStatus.BAD_REQUEST, 100001, "유효하지 않은 포인트 충전 금액"),
    INVALID_USE_AMOUNT(HttpStatus.BAD_REQUEST, 100002, "유효하지 않은 포인트 사용 금액");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
