package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.common.exception.CartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartTest {

    Cart cart;

    @BeforeEach
    public void setUp() {
        Product product = Product.builder()
                .id(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(10L)
                .build();

        cart = Cart.create(1L, product);
    }

    @Test
    @DisplayName("장바구니 상품 수량 증가 성공")
    void createCart_success() {
        // Given
        long increaseQuantity = 10L;

        // When
        cart.increaseQuantity(increaseQuantity);

        // Then
        assertEquals(10L, cart.getQuantity());
    }


    @Test
    @DisplayName("장바구니 상품 증가 수량이 0 보다 작거나 같은 경우, 장바구니 상품 수량 증가 실패")
    void createCart_fail_amount_is_zero_or_negative() {
        // Given
        long increaseQuantity = 0L;

        // When & Then
        assertThrows(CartException.class, () -> cart.increaseQuantity(increaseQuantity));
    }
}
