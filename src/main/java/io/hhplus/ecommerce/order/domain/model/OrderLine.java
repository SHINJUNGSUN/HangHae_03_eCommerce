package io.hhplus.ecommerce.order.domain.model;

import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    private Long orderLineId;

    private Long orderId;

    private Long productId;

    private String productName;

    private Long unitPrice;

    private Long quantity;

    public static OrderLine create(long orderId, long amount, Product product) {
        return OrderLine.builder()
                .orderId(orderId)
                .productId(product.getProductId())
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .quantity(amount)
                .build();
    }

    public Long totalPrice() {
        return quantity * unitPrice;
    }
}
