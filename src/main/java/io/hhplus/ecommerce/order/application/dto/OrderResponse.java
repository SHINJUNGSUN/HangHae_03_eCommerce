package io.hhplus.ecommerce.order.application.dto;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record OrderResponse (
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
        return new OrderResponse(
                order.getOrderId(),
                order.getOrderStatus(),
                order.getOrderLines().size(),
                order.totalPrice()
        );
    }
}
