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

    public static OrderEntity of(Order order) {
        return OrderEntity.builder()
                .id(order.getOrderId())
                .userSeq(order.getUserSeq())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public Order toDomain() {
        return Order.builder()
                .orderId(this.id)
                .userSeq(this.userSeq)
                .orderStatus(this.orderStatus)
                .orderLines(new ArrayList<>())
                .build();
    }

//    public static OrderEntity create(long userId) {
//        return OrderEntity.builder()
//                .userId(userId)
//                .orderStatus(OrderStatus.PENDING)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .orderLines(new ArrayList<>())
//                .build();
//    }
//
//    public void addOrderLine(OrderLineRequest orderLineRequest) {
//        this.orderLines.add(
//                OrderLine.builder()
//                        .productId(orderLineRequest.product().getId())
//                        .productName(orderLineRequest.product().getProductName())
//                        .unitPrice(orderLineRequest.product().getUnitPrice())
//                        .quantity(orderLineRequest.quantity())
//                        .createdAt(LocalDateTime.now())
//                        .order(this)
//                        .build());
//    }
//
//    public void completeOrder() {
//        this.orderStatus = OrderStatus.COMPLETED;
//    }
//

}
