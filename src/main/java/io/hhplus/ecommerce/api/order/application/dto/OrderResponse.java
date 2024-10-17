package io.hhplus.ecommerce.api.order.application.dto;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record OrderResponse(
        @Schema(description = "주문 고유 식별자")
        Long orderId,
        @Schema(description = "주문 상태 (PENDING: 주문 대기, COMPLETED: 주문 완료, CANCELED: 주문 취소)")
        OrderStatus orderStatus,
        @Schema(description = "주문 상품 수량")
        Integer productCount,
        @Schema(description = "주문 금액")
        Long totalPrice
) {
    public static OrderResponse from(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .productCount(order.getOrderLines().size())
                .totalPrice(order.totalPrice())
                .build();
    }
}
