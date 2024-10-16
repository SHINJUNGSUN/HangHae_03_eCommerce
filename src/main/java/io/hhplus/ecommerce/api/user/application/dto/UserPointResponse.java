package io.hhplus.ecommerce.api.user.application.dto;

import io.hhplus.ecommerce.api.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserPointResponse (
        @Schema(description = "사용자 고유 식별자")
        Long userId,
        @Schema(description = "사용자 이름")
        String userName,
        @Schema(description = "포인트 잔액")
        Long point
) {
    public static UserPointResponse from(User user) {
        return UserPointResponse.builder()
                .userId(user.getId())
                .userName(user.getUserName())
                .point(user.getPoint())
                .build();
    }
}
