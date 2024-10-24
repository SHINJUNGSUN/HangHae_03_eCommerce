package io.hhplus.ecommerce.order.infrastructure.persistence.entity;

import io.hhplus.ecommerce.common.model.TimeStamped;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_line")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long productId;

    private String productName;

    private Long unitPrice;

    private Long quantity;

    public static OrderLineEntity from(OrderLine orderLine) {
        return OrderLineEntity.builder()
                .id(orderLine.getOrderLineId())
                .orderId(orderLine.getOrderId())
                .productId(orderLine.getProductId())
                .productName(orderLine.getProductName())
                .unitPrice(orderLine.getUnitPrice())
                .quantity(orderLine.getQuantity())
                .build();
    }

    public OrderLine toOrderLine() {
        return OrderLine.builder()
                .orderLineId(this.id)
                .orderId(this.orderId)
                .productId(this.productId)
                .productName(this.productName)
                .unitPrice(this.unitPrice)
                .quantity(this.quantity)
                .build();
    }
}
