package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.cart.domain.exception.CartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartTest {

    Cart cart;

    Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .productId(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(1L)
                .build();

        cart = Cart.create(1L, 1L);
    }

    @Test
    @DisplayName("장바구니 상품 수량 증가 성공")
    void addCart_success() {
        // Given
        long quantity = 1L;

        // When
        cart.addCart(quantity, product);

        // Then
        assertEquals(1L, cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니 상품 수량 증가 실패: 장바구니 상품 증가 수량이 0 보다 작거나 같은 경우")
    void addCart_fail_quantity_is_zero_or_negative() {
        // Given
        long quantity = -1L;

        // When & Then
        assertThrows(CartException.class, () -> cart.addCart(quantity, product));
    }

    @Test
    @DisplayName("장바구니 상품 수량 증가 실패: 장바구니 상품 증가 수량이 상품 재고보다 큰 경우")
    void createCart_fail_quantity_exceeds_stock() {
        // Given
        long quantity = 2L;

        // When & Then
        assertThrows(CartException.class, () -> cart.addCart(quantity, product));
    }
}
