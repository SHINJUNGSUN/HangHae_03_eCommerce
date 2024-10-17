package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.api.cart.application.CartApplicationService;
import io.hhplus.ecommerce.api.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.api.user.domain.model.User;
import io.hhplus.ecommerce.api.user.domain.repository.UserRepository;
import io.hhplus.ecommerce.common.exception.CartException;
import io.hhplus.ecommerce.common.exception.ProductException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CartIntegrationTest {

    @Autowired
    CartApplicationService cartApplicationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    User user;

    List<Product> products = new ArrayList<>();

    List<Cart> carts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder().userName("Alice").point(0L).build());

        products.add(productRepository.save(Product.builder().productName("Laptop").unitPrice(1500000L).stock(10L).build()));
        products.add(productRepository.save(Product.builder().productName("Smartphone").unitPrice(800000L).stock(20L).build()));

        carts.add(cartRepository.save(Cart.builder().userId(user.getId()).quantity(1L).product(products.get(0)).build()));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("장바구니 목록 조회 성공")
    void getPoint() {
        // Given
        long userId = user.getId();

        // When
        List<CartResponse> response = cartApplicationService.getCarts(userId);

        // Then
        assertEquals(carts.size(), response.size());
    }

    @Test
    @DisplayName("장바구니 추가 성공: 장바구니에 있는 상품을 추가한 경우")
    void addCart_cart_exists() {
        // Given
        long userId = user.getId();
        long productId = products.get(0).getId();
        long quantity = 3L;

        // When
        List<CartResponse> response = cartApplicationService.addCart(new CartAddRequest(userId, productId, quantity));

        // Then
        assertEquals(4L, response.get(0).quantity());
    }

    @Test
    @DisplayName("장바구니 추가 성공: 장바구니에 없는 상품을 추가한 경우")
    void addCart_cart_does_not_exist() {
        // Given
        long userId = user.getId();
        long productId = products.get(1).getId();
        long quantity = 3L;

        // When
        List<CartResponse> response = cartApplicationService.addCart(new CartAddRequest(userId, productId, quantity));

        // Then
        assertEquals(carts.size() + 1, response.size());
    }

    @Test
    @DisplayName("장바구니 추가 실패: 상품이 존재하지 않는 경우")
    void addCart_fail_product_does_not_exist() {
        // Given
        long userId = user.getId();
        long productId = 0L;
        long quantity = 3L;

        // When & Then
        assertThrows(ProductException.class, () -> cartApplicationService.addCart(new CartAddRequest(userId, productId, quantity)));
    }

    @Test
    @DisplayName("장바구니 제거 성공")
    void removeCart() {
        // Given
        long userId = user.getId();
        long productId = products.get(0).getId();

        // When
        List<CartResponse> response = cartApplicationService.removeCart(new CartRemoveRequest(userId, productId));

        // Then
        assertEquals(carts.size() - 1, response.size());
    }

    @Test
    @DisplayName("장바구니 제거 실패: 장바구니에 없는 상품을 제거한 경우")
    void addCart_fail_cart_does_not_exist() {
        // Given
        long userId = user.getId();
        long productId = 0L;

        // When & Then
        assertThrows(CartException.class, () -> cartApplicationService.removeCart(new CartRemoveRequest(userId, productId)));
    }
}
