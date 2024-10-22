package io.hhplus.ecommerce.cart.domain.model;

import io.hhplus.ecommerce.cart.domain.exception.CartException;
import io.hhplus.ecommerce.cart.domain.exception.CartExceptionType;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Long cartId;

    private Long userSeq;

    @Builder.Default
    private Long quantity = 0L;

    private Product product;

    public static Cart create(long userSeq, Product product) {
        return Cart.builder()
                .userSeq(userSeq)
                .product(product)
                .build();
    }

    public void addCart(long amount) {

        if (amount <= 0) {
            throw new CartException(CartExceptionType.INVALID_AMOUNT);
        }

        if (product.getStock() < this.quantity + amount) {
            throw new CartException(CartExceptionType.INSUFFICIENT_STOCK);
        }

        this.quantity += amount;
    }
}
