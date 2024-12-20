package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .productId(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(1L)
                .build();
    }

    @Test
    @DisplayName("상품 재고 감소 성공")
    void reduceProduct_success() {
        // Given
        long quantity = 1L;

        // When
        product.reduceStock(quantity);

        // Then
        assertEquals(0L, product.getStock());
    }

    @Test
    @DisplayName("상품 재고 감소 실패: 상품 재고 감소 수량이 0 보다 작거나 같은 경우")
    void usePoint_fail_amount_is_zero_or_negative() {
        // Given
        long quantity = -1L;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> product.reduceStock(quantity));
    }

    @Test
    @DisplayName("상품 재고 감소 실패: 상품 재고 감소 수량이 상품 재고보다 큰 경우")
    void usePoint_fail_amount_exceeds_balance() {
        // Given
        long quantity = 2L;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> product.reduceStock(quantity));
    }
}
