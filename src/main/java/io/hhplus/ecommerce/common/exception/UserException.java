package io.hhplus.ecommerce.common.exception;

import io.hhplus.ecommerce.common.enums.UserError;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final UserError error;

    public UserException(final UserError error) {
        this.error = error;
    }
}
