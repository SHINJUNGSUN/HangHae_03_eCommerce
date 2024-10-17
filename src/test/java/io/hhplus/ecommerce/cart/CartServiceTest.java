package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.api.cart.domain.service.CartDomainService;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.common.exception.CartException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class CartServiceTest {

    @InjectMocks
    CartDomainService cartDomainService;

    @Mock
    CartRepository cartRepository;

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
    @DisplayName("장바구니에 해당 상품이 이미 존재하는 경우, 장바구니 상품 추가 성공")
    void addCart_success_cart_exists() {
        // Given
        long userId = 1L;
        long increaseQuantity = 3L;
        long existingQuantity = 4L;
        Cart cart = Cart.builder()
                .userId(userId)
                .quantity(existingQuantity)
                .product(product)
                .build();

        when(cartRepository.findByUserIdAndProductId(userId, product.getId()))
                .thenReturn(Optional.of(cart));
        when(cartRepository.findByUserId(userId)).thenReturn(List.of(cart));

        // when
        cartDomainService.addCart(userId, increaseQuantity, product);

        // That
        assertThat(cart.getQuantity()).isEqualTo(increaseQuantity + existingQuantity);
    }

    @Test
    @DisplayName("장바구니에 해당 상품이 존재하지 않는 경우, 장바구니 상품 추가 성공")
    void addCart_success_cart_does_not_exist() {
        // Given
        long userId = 1L;
        long increaseQuantity = 3L;
        Cart cart = Cart.builder()
                .userId(userId)
                .quantity(increaseQuantity)
                .product(product)
                .build();

        when(cartRepository.findByUserIdAndProductId(userId, product.getId()))
                .thenReturn(Optional.empty());
        when(cartRepository.findByUserId(userId)).thenReturn(List.of(cart));

        // when
        List<Cart> response = cartDomainService.addCart(userId, increaseQuantity, product);

        // That
        assertThat(response).contains(cart);
        assertThat(cart.getQuantity()).isEqualTo(increaseQuantity);
    }

    @Test
    @DisplayName("장바구니에 해당 상품이 이미 존재하는 경우, 장바구니 상품 제거 성공")
    void removeCart_success_cart_exists() {
        // Given
        long userId = 1L;
        long productId = 1L;
        Cart cart = Cart.builder()
                .userId(userId)
                .quantity(1L)
                .product(product)
                .build();

        when(cartRepository.findByUserIdAndProductId(userId, product.getId()))
                .thenReturn(Optional.of(cart));
        when(cartRepository.findByUserId(userId)).thenReturn(List.of());

        // when
        List<Cart> response = cartDomainService.removeCart(userId, productId);

        // That
        assertThat(response).isEmpty();
    }

    @Test
    @DisplayName("장바구니에 해당 상품이 존재하지 않는 경우, 장바구니 상품 제거 실패")
    void removeCart_fail_cart_does_not_exist() {
        // Given
        long userId = 1L;
        long productId = 1L;

        when(cartRepository.findByUserIdAndProductId(userId, productId))
                .thenReturn(Optional.empty());

        // When & Then
        assertThrows(CartException.class, () -> cartDomainService.removeCart(userId, productId));
    }
}
