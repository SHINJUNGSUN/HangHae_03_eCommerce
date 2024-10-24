package io.hhplus.ecommerce.user.application.dto;

import io.hhplus.ecommerce.user.domain.model.User;

public record UserSignUpResponse(
        String userId,
        String userName
) {
    public static UserSignUpResponse from(User user) {
        return new UserSignUpResponse(
                user.getUserId(),
                user.getUserName()
        );
    }
}
