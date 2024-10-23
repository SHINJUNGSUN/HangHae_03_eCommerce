package io.hhplus.ecommerce.cart.infrastructure.persistence.entity;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.common.model.TimeStamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userSeq;

    private Long quantity;

    private Long productId;

    public static CartEntity from(Cart cart) {
        return CartEntity.builder()
                .id(cart.getCartId())
                .userSeq(cart.getUserSeq())
                .quantity(cart.getQuantity())
                .productId(cart.getProductId())
                .build();
    }

    public Cart toCart() {
        return Cart.builder()
                .cartId(this.id)
                .userSeq(this.userSeq)
                .quantity(this.quantity)
                .productId(this.productId)
                .build();
    }
}
