package io.hhplus.ecommerce.api.order.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record OrderRequest (
        @Schema(description = "사용자 고유 식별자")
        Long userId,
        @Schema(description = "주문 상품 목록")
        List<OrderProduct> OrderProductList
) {
}
