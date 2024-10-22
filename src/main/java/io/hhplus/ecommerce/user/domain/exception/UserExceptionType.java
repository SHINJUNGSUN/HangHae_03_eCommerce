package io.hhplus.ecommerce.user.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserExceptionType {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 100001, "존재하지 않는 사용자입니다."),
    INVALID_AMOUNT(HttpStatus.BAD_REQUEST, 100002, "유효하지 않은 포인트 금액입니다."),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, 100003, "포인트 잔액이 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
