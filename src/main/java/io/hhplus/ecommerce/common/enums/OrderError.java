package io.hhplus.ecommerce.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum OrderError {
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, 400001, "존재하지 않는 주문입니다.");

    HttpStatus status;
    int errorCode;
    String errorMessage;
}
