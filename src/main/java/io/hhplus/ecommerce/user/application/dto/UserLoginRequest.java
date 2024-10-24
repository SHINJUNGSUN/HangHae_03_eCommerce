package io.hhplus.ecommerce.user.application.dto;

public record UserLoginRequest (
        String userId,
        String password
) {
}
