package io.hhplus.ecommerce.order.infrastructure.persistence.entity;

import io.hhplus.ecommerce.common.model.TimeStamped;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userSeq;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .id(order.getOrderId())
                .userSeq(order.getUserSeq())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public Order toOrder() {
        return Order.builder()
                .orderId(this.id)
                .userSeq(this.userSeq)
                .orderStatus(this.orderStatus)
                .orderLines(new ArrayList<>())
                .build();
    }
}
