package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.cart.application.service.CartApplicationService;
import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class CartServiceTest {

    @InjectMocks
    CartApplicationService cartApplicationService;

    @Mock
    CartRepository cartRepository;

    Cart cart;

    Product product;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        product = Product.builder()
                .productId(1L)
                .productName("Laptop")
                .unitPrice(1500000L)
                .stock(2L)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("장바구니 상품 추가 성공: 장바구니에 해당 상품이 이미 존재하는 경우")
    void addCart_success_cart_exists() {
        // Given
        long userSeq = 1L;
        long quantity = 1L;
        long prevQuantity = 1L;
        cart = Cart.builder()
                .userSeq(userSeq)
                .quantity(prevQuantity)
                .productId(product.getProductId())
                .build();

        when(cartRepository.findByUserSeqAndProductId(userSeq, product.getProductId())).thenReturn(Optional.of(cart));
        when(cartRepository.findByUserSeq(userSeq)).thenReturn(List.of(cart));

        // when
        cartApplicationService.addCart(userSeq, quantity, product);

        // That
        assertEquals(2L, cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니 상품 추가 성공: 장바구니에 해당 상품이 존재하지 않는 경우")
    void addCart_success_cart_does_not_exist() {
        // Given
        long userSeq = 1L;
        long quantity = 1L;
        cart = Cart.builder()
                .userSeq(userSeq)
                .quantity(quantity)
                .productId(product.getProductId())
                .build();

        when(cartRepository.findByUserSeqAndProductId(userSeq, product.getProductId())).thenReturn(Optional.empty());
        when(cartRepository.findByUserSeq(userSeq)).thenReturn(List.of(cart));

        // when
        cartApplicationService.addCart(userSeq, quantity, product);

        // That
        assertEquals(1L, cart.getQuantity());
    }

    @Test
    @DisplayName("장바구니 상품 제거 성공: 장바구니에 해당 상품이 이미 존재하는 경우")
    void removeCart_success_cart_exists() {
        // Given
        long userSeq = 1L;
        long productId = 1L;
        cart = Cart.builder()
                .userSeq(userSeq)
                .quantity(1L)
                .productId(product.getProductId())
                .build();

        when(cartRepository.findByUserSeqAndProductId(userSeq, product.getProductId())).thenReturn(Optional.of(cart));
        when(cartRepository.findByUserSeq(userSeq)).thenReturn(List.of());

        // when
        List<Cart> response = cartApplicationService.removeCart(userSeq, productId);

        // That
        assertThat(response).isEmpty();
    }

    @Test
    @DisplayName("장바구니 상품 제거 실패: 장바구니에 해당 상품이 존재하지 않는 경우")
    void removeCart_fail_cart_does_not_exist() {
        // Given
        long userSeq = 1L;
        long productId = 1L;

        when(cartRepository.findByUserSeqAndProductId(userSeq, product.getProductId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalStateException.class, () -> cartApplicationService.removeCart(userSeq, productId));
    }
}
