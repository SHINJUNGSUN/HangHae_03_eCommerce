package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.cart.infrastructure.persistence.entity.CartEntity;
import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import io.hhplus.ecommerce.cart.domain.exception.CartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartEntityTest {

    CartEntity cartEntity;

    @BeforeEach
    public void setUp() {
        ProductEntity product = ProductEntity.builder()
                .id(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(10L)
                .build();

        cartEntity = CartEntity.create(1L, product);
    }

    @Test
    @DisplayName("장바구니 상품 수량 증가 성공")
    void createCart_success() {
        // Given
        long increaseQuantity = 10L;

        // When
        cartEntity.increaseQuantity(increaseQuantity);

        // Then
        assertEquals(10L, cartEntity.getQuantity());
    }


    @Test
    @DisplayName("장바구니 상품 증가 수량이 0 보다 작거나 같은 경우, 장바구니 상품 수량 증가 실패")
    void createCart_fail_amount_is_zero_or_negative() {
        // Given
        long increaseQuantity = 0L;

        // When & Then
        assertThrows(CartException.class, () -> cartEntity.increaseQuantity(increaseQuantity));
    }
}
