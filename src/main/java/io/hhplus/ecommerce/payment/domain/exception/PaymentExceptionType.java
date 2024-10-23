package io.hhplus.ecommerce.payment.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PaymentExceptionType {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, 400001, "존재하지 않는 주문입니다."),
    CANCELED_ORDER(HttpStatus.NOT_FOUND, 400002, "취소된 주문입니다."),
    COMPLETED_ORDER(HttpStatus.NOT_FOUND, 400003, "이미 완료된 주문입니다."),
    INSUFFICIENT_POINT(HttpStatus.NOT_FOUND, 400004, "포인트가 부족합니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
