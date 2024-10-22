package io.hhplus.ecommerce.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserPointRequest(
        @Schema(description = "사용자 고유 식별자")
        Long userSeq,
        @Schema(description = "충전/사용 포인트")
        Long amount
) {
}
