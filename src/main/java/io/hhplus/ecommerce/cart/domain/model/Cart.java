package io.hhplus.ecommerce.cart.domain.model;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    private Long cartId;

    private Long userSeq;

    @Builder.Default
    private Long quantity = 0L;

    private Long productId;

    public static Cart create(long userSeq, long productId) {
        return Cart.builder()
                .userSeq(userSeq)
                .productId(productId)
                .build();
    }

    public void addCart(long quantity, Product product) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_QUANTITY.getMessage());
        }

        if (product.getStock() < this.quantity + quantity) {
            throw new IllegalArgumentException(ExceptionMessage.INSUFFICIENT_STOCK.getMessage());
        }

        this.quantity += quantity;
    }
}
