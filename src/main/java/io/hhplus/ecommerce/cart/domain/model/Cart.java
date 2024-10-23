package io.hhplus.ecommerce.cart.domain.model;

import io.hhplus.ecommerce.cart.domain.exception.CartException;
import io.hhplus.ecommerce.cart.domain.exception.CartExceptionType;
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
            throw new CartException(CartExceptionType.INVALID_AMOUNT);
        }

        if (product.getStock() < this.quantity + quantity) {
            throw new CartException(CartExceptionType.INSUFFICIENT_STOCK);
        }

        this.quantity += quantity;
    }
}
