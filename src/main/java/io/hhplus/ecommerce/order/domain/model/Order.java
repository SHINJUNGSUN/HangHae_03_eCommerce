package io.hhplus.ecommerce.order.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private long orderId;

    private long userSeq;

    private OrderStatus orderStatus;

    private List<OrderLine> orderLines;

    public static Order create(long userSeq) {
        return Order.builder()
                .userSeq(userSeq)
                .orderStatus(OrderStatus.PENDING)
                .orderLines(new ArrayList<>())
                .build();
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public Long totalPrice() {
        return orderLines.stream()
                .mapToLong(OrderLine::totalPrice)
                .sum();
    }

    public void completeOrder() {
        this.orderStatus = OrderStatus.COMPLETED;
    }
}
