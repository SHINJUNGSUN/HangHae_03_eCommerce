package io.hhplus.ecommerce.api.cart.domain.model;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.common.enums.CartError;
import io.hhplus.ecommerce.common.exception.CartException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static Cart create(long userId, Product product) {
        return Cart.builder()
                .userId(userId)
                .product(product)
                .quantity(0L)
                .build();
    }

    public void increaseQuantity(long quantity) {

        if(quantity <= 0) {
            throw new CartException(CartError.INVALID_QUANTITY);
        }

        this.quantity += quantity;
    }
}
