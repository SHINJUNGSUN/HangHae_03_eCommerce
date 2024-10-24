package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.product.application.service.ProductApplicationService;
import io.hhplus.ecommerce.product.domain.exception.ProductException;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ProductServiceTest {

    @InjectMocks
    ProductApplicationService productApplicationService;

    @Mock
    ProductRepository productRepository;

    Product product;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        product = Product.builder()
                .productId(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(1L)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("상품 조회 성공")
    public void getProduct_success() {
        // Given
        long productId = 1L;
        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> response = productApplicationService.getProduct(productId);

        // When
        assertTrue(response.isPresent());
        assertEquals(product, response.get());
    }

    @Test
    @DisplayName("상품 목록 조회 성공")
    public void getProducts_success() {
        // Given
        when(productRepository.findAll()).thenReturn(List.of(product));

        // Act
        List<Product> response = productApplicationService.getProducts();

        // When
        assertThat(response)
                .hasSize(1)
                .extracting(i -> tuple(i.getProductId(), i.getProductName(), i.getStock(), i.getUnitPrice()))
                .containsExactly(
                        tuple(product.getProductId(), product.getProductName(), product.getStock(), product.getUnitPrice())
                );
    }

    @Test
    @DisplayName("상품 수량 감소 성공")
    public void reduceProduct_success() {
        // Given
        long productId = 1L;
        long quantity = 1L;

        when(productRepository.findByProductId(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product response = productApplicationService.reduceProduct(productId, quantity);

        // When
        assertEquals(product.getStock(), response.getStock());
    }

    @Test
    @DisplayName("상품 수량 감소 실패: 상품이 존재하지 않는 경우")
    public void reduceProduct_fail_productNotFound() {
        // Given
        long productId = 1L;
        long quantity = 1L;
        when(productRepository.findByProductId(productId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductException.class, () -> productApplicationService.reduceProduct(productId, quantity));
    }
}
