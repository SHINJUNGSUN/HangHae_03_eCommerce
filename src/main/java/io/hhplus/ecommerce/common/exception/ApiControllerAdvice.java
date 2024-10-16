package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.enums.UserError;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.common.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {
    @ExceptionHandler(value = UserException.class)
    public CommonApiResponse<ErrorResponse> handleUserException(UserException e) {
        UserError error = e.getError();
        log.error("[{}] User Error: {}", error.getStatus(), error.getErrorMessage(), e);
        return CommonApiResponse.error(error.getStatus(), error.getErrorCode(), error.getErrorMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonApiResponse<ErrorResponse> handleException(Exception e) {
        log.error("[{}] Service Error: {}", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        return CommonApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, 999999, "알 수 없는 에러가 발생하였습니다.");
    }
}
