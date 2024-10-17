package io.hhplus.ecommerce.api.order.domain.model;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import lombok.Builder;

@Builder
public record OrderLineRequest(
        Product product,
        Long quantity
) {
    public static OrderLineRequest of(Product product, Long quantity) {
        return OrderLineRequest.builder()
                .product(product)
                .quantity(quantity)
                .build();
    }
}
