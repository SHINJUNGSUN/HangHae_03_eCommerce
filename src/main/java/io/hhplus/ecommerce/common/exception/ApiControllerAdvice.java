package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.common.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler(value = IllegalStateException.class)
    public CommonApiResponse<ErrorResponse> handleIllegalStateException(IllegalStateException e) {
        log.error("[{}] IllegalStateException: {}", HttpStatus.BAD_REQUEST, e.getMessage(), e);
        return CommonApiResponse.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonApiResponse<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("[{}] IllegalArgumentException: {}", HttpStatus.BAD_REQUEST, e.getMessage(), e);
        return CommonApiResponse.error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonApiResponse<ErrorResponse> handleException(Exception e) {
        log.error("[{}] Exception: {}", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        return CommonApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생하였습니다.");
    }
}
