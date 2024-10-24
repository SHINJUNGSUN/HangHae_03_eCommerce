package io.hhplus.ecommerce.cart;

import io.hhplus.ecommerce.cart.application.CartFacade;
import io.hhplus.ecommerce.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.model.UserPoint;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class CartIntegrationTest {

    @Container
    static MySQLContainer<?> mySQLContainer;

    static {
        mySQLContainer = new MySQLContainer<>("mysql:8.0");
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Autowired
    CartFacade cartFacade;

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
        user = userRepository.save(User.builder()
                        .userId("alice123")
                        .password("1q2w3e4r!!")
                        .userName("Alice")
                        .userPoint(UserPoint.of(0L))
                        .build());

        products.add(productRepository.save(Product.builder().productName("Laptop").unitPrice(1500000L).stock(10L).build()));
        products.add(productRepository.save(Product.builder().productName("Smartphone").unitPrice(800000L).stock(20L).build()));

        carts.add(Cart.builder().userSeq(user.getUserSeq()).quantity(1L).productId(products.get(0).getProductId()).build());
        cartRepository.save(carts.get(0));
    }

    @Test
    @DisplayName("장바구니 목록 조회 성공")
    void getPoint() {
        // Given
        long userSeq = user.getUserSeq();

        // When
        List<CartResponse> response = cartFacade.getCarts(userSeq);

        // Then
        assertThat(response)
                .hasSize(1)
                .extracting(i -> tuple(i.productId(), i.quantity()))
                .containsExactlyElementsOf(
                        carts.stream()
                                .map(cart -> tuple(cart.getProductId(), cart.getQuantity()))
                                .toList()
                );
    }

    @Test
    @DisplayName("장바구니 추가 성공: 장바구니에 있는 상품을 추가한 경우")
    void addCart_cart_exists() {
        // Given
        long userSeq = user.getUserSeq();
        long productId = products.get(0).getProductId();
        long quantity = 3L;

        // When
        List<CartResponse> response = cartFacade.addCart(userSeq, new CartAddRequest(productId, quantity));

        // Then
        assertThat(response)
                .hasSize(1)
                .extracting(i -> tuple(i.productId(), i.quantity()))
                .containsExactlyElementsOf(
                        cartFacade.getCarts(userSeq).stream()
                                .map(cart -> tuple(cart.productId(), cart.quantity()))
                                .toList()
                );
    }

    @Test
    @DisplayName("장바구니 추가 성공: 장바구니에 없는 상품을 추가한 경우")
    void addCart_cart_does_not_exist() {
        // Given
        long userSeq = user.getUserSeq();
        long productId = products.get(1).getProductId();
        long quantity = 3L;

        // When
        List<CartResponse> response = cartFacade.addCart(userSeq, new CartAddRequest(productId, quantity));

        // Then
        assertThat(response)
                .hasSize(2)
                .extracting(i -> tuple(i.productId(), i.quantity()))
                .containsExactlyElementsOf(
                        cartFacade.getCarts(userSeq).stream()
                                .map(cart -> tuple(cart.productId(), cart.quantity()))
                                .toList()
                );
    }

    @Test
    @DisplayName("장바구니 추가 실패: 상품이 존재하지 않는 경우")
    void addCart_fail_product_does_not_exist() {
        // Given
        long userSeq = user.getUserSeq();
        long productId = 0L;
        long quantity = 3L;

        // When & Then
        assertThrows(IllegalStateException.class, () -> cartFacade.addCart(userSeq, new CartAddRequest(productId, quantity)));
    }

    @Test
    @DisplayName("장바구니 제거 성공")
    void removeCart() {
        // Given
        long userSeq = user.getUserSeq();
        long productId = products.get(0).getProductId();

        // When
        List<CartResponse> response = cartFacade.removeCart(userSeq, new CartRemoveRequest(productId));

        // Then
        assertThat(response).isEmpty();
    }

    @Test
    @DisplayName("장바구니 제거 실패: 장바구니에 없는 상품을 제거한 경우")
    void addCart_fail_cart_does_not_exist() {
        // Given
        long userSeq = user.getUserSeq();
        long productId = 0L;

        // When & Then
        assertThrows(IllegalStateException.class, () -> cartFacade.removeCart(userSeq, new CartRemoveRequest(productId)));
    }
}