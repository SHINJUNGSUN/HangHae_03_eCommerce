package io.hhplus.ecommerce.user.application.dto;

import io.hhplus.ecommerce.user.domain.model.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserPointResponse (
        @Schema(description = "사용자 고유 식별자")
        Long userSeq,
        @Schema(description = "포인트 잔액")
        Long point
) {
    public static UserPointResponse from(User user) {
        return new UserPointResponse(
                user.getUserSeq(),
                user.getUserPoint().getPoint()
        );
    }
}
