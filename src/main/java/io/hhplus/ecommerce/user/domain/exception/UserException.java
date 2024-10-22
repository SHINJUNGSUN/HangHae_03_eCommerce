package io.hhplus.ecommerce.user.domain.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final UserExceptionType exception;

    public UserException(UserExceptionType exception) {
        this.exception = exception;
    }
}
