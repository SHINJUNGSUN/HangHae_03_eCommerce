package io.hhplus.ecommerce.api.user.application.dto;

import io.hhplus.ecommerce.api.user.domain.User;
import lombok.Builder;

@Builder
public record UserPointResponse (
        Long id,
        String userName,
        Long point
) {
    public static UserPointResponse from(User user) {
        return UserPointResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .point(user.getPoint())
                .build();
    }
}
