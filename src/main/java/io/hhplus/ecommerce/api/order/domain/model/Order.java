package io.hhplus.ecommerce.api.order.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    public static Order create(long userId) {
        return Order.builder()
                .userId(userId)
                .orderStatus(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .orderLines(new ArrayList<>())
                .build();
    }

    public void addOrderLine(OrderLineRequest orderLineRequest) {
        this.orderLines.add(
                OrderLine.builder()
                        .productId(orderLineRequest.product().getId())
                        .productName(orderLineRequest.product().getProductName())
                        .unitPrice(orderLineRequest.product().getUnitPrice())
                        .quantity(orderLineRequest.quantity())
                        .createdAt(LocalDateTime.now())
                        .order(this)
                        .build());
    }

    public void completeOrder() {
        this.orderStatus = OrderStatus.COMPLETED;
    }

    public Long totalPrice() {
        return orderLines.stream()
                .mapToLong(OrderLine::totalPrice)
                .sum();
    }
}
