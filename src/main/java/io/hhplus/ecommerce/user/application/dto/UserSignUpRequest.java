package io.hhplus.ecommerce.user.application.dto;

public record UserSignUpRequest (
        String userId,
        String password,
        String userName
) {
}
