package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductEntityTest {

    ProductEntity product;

    @BeforeEach
    public void setUp() {
        product = ProductEntity.builder()
                .id(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(10L)
                .build();
    }

    @Test
    @DisplayName("재고 감소 성공")
    void reduceProduct_success() {
        // Given
        long quantity = 5L;

        // When
        product.reduceProduct(quantity);

        // Then
        assertEquals(5L, product.getStock());
    }

    @Test
    @DisplayName("감소 수량이 0 보다 작거나 같은 경우, 재고 감소 실패")
    void usePoint_fail_amount_is_zero_or_negative() {
        // Given
        long quantity = -5L;

        // When & Then
        assertThrows(ProductException.class, () -> product.reduceProduct(quantity));
    }

    @Test
    @DisplayName("감소 수량이 재고보다 큰 경우, 재고 감소 실패")
    void usePoint_fail_amount_exceeds_balance() {
        // Given
        long quantity = 15L;

        // When & Then
        assertThrows(ProductException.class, () -> product.reduceProduct(quantity));
    }
}
