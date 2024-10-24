package io.hhplus.ecommerce.user.application.dto;

public record UserLoginResponse(
        String token
) {
    public static UserLoginResponse of(String token) {
        return new UserLoginResponse(token);
    }
}
