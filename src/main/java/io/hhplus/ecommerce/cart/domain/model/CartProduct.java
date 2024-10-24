package io.hhplus.ecommerce.cart.domain.model;

import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct {

    private Cart cart;

    private Product product;

    private CartProductState cartProductState;

    public static CartProduct ofCartAndProduct(Cart cart, Product product) {

        return CartProduct.builder()
                .cart(cart)
                .product(product)
                .cartProductState(
                        product.getStock() < cart.getQuantity()
                                ? CartProductState.OUT_OF_STOCK : CartProductState.AVAILABLE)
                .build();
    }
}
