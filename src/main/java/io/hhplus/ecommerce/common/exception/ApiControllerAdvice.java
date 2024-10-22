package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.cart.domain.exception.CartException;
import io.hhplus.ecommerce.cart.domain.exception.CartExceptionType;
import io.hhplus.ecommerce.order.domain.exception.OrderExceptionType;
import io.hhplus.ecommerce.order.domain.exception.OrderException;
import io.hhplus.ecommerce.payment.domain.exception.PaymentException;
import io.hhplus.ecommerce.payment.domain.exception.PaymentExceptionType;
import io.hhplus.ecommerce.user.domain.exception.UserExceptionType;
import io.hhplus.ecommerce.common.model.CommonApiResponse;
import io.hhplus.ecommerce.common.model.ErrorResponse;
import io.hhplus.ecommerce.user.domain.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler(value = CartException.class)
    public CommonApiResponse<ErrorResponse> handleCartException(CartException e) {
        CartExceptionType exception = e.getException();
        log.error("[{}] Cart Exception: {}", exception.getStatus(), exception.getErrorMessage(), e);
        return CommonApiResponse.error(exception.getStatus(), exception.getErrorCode(), exception.getErrorMessage());
    }

    @ExceptionHandler(value = UserException.class)
    public CommonApiResponse<ErrorResponse> handleUserException(UserException e) {
        UserExceptionType exception = e.getException();
        log.error("[{}] User Error: {}", exception.getStatus(), exception.getErrorMessage(), e);
        return CommonApiResponse.error(exception.getStatus(), exception.getErrorCode(), exception.getErrorMessage());
    }

    @ExceptionHandler(value = PaymentException.class)
    public CommonApiResponse<ErrorResponse> handleProductException(PaymentException e) {
        PaymentExceptionType exception = e.getException();
        log.error("[{}] Payment Error: {}", exception.getStatus(), exception.getErrorMessage(), e);
        return CommonApiResponse.error(exception.getStatus(), exception.getErrorCode(), exception.getErrorMessage());
    }

    @ExceptionHandler(value = OrderException.class)
    public CommonApiResponse<ErrorResponse> handleOrderException(OrderException e) {
        OrderExceptionType exception = e.getException();
        log.error("[{}] Order Error: {}", exception.getStatus(), exception.getErrorMessage(), e);
        return CommonApiResponse.error(exception.getStatus(), exception.getErrorCode(), exception.getErrorMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonApiResponse<ErrorResponse> handleException(Exception e) {
        log.error("[{}] Service Error: {}", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        return CommonApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, 999999, "알 수 없는 에러가 발생하였습니다.");
    }
}
