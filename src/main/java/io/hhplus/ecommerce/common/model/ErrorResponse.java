package io.hhplus.ecommerce.common.model;

public record ErrorResponse(
        int errorCode,
        String errorMessage
) {
}
