package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.api.product.domain.service.ProductDomainService;
import io.hhplus.ecommerce.common.exception.ProductException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    ProductDomainService productDomainService;

    @Mock
    ProductRepository productRepository;

    Product product;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        product = Product.builder()
                .id(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(10L)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("상품 수량 감소 성공 테스트")
    public void reduceProduct_Success() {
        // Given
        long productId = 1L;
        long quantity = 3L;

        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product updatedProduct = productDomainService.reduceProduct(productId, quantity);

        // Then
        assertThat(updatedProduct.getStock()).isEqualTo(7L);
    }

    @Test
    @DisplayName("상품이 존재하지 않는 경우, 재고 감소 실패")
    public void reduceProduct_fail_product_not_found() {
        // Given
        long productId = 1L;
        long quantity = 2L;
        when(productRepository.findByProductId(productId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductException.class, () -> productDomainService.reduceProduct(productId, quantity));
    }
}
