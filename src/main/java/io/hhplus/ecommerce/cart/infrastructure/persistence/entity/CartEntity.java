package io.hhplus.ecommerce.cart.infrastructure.persistence.entity;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.common.model.TimeStamped;
import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
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

    public static CartEntity of(Cart cart) {
        return CartEntity.builder()
                .id(cart.getCartId())
                .userSeq(cart.getUserSeq())
                .quantity(cart.getQuantity())
                .productId(cart.get)
                .build();
    }

    public Cart toDomain() {
        return Cart.builder()
                .cartId(this.id)
                .userSeq(this.userSeq)
                .quantity(this.quantity)
                .product(product.toDomain())
                .build();
    }
}
