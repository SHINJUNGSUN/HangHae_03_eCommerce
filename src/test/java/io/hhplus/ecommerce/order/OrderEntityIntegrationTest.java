package io.hhplus.ecommerce.order;

import io.hhplus.ecommerce.order.application.service.OrderApplicationService;
import io.hhplus.ecommerce.order.application.dto.OrderProduct;
import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.user.infrastructure.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
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
public class OrderEntityIntegrationTest {

    @Autowired
    OrderApplicationService orderApplicationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    User user;

    List<ProductEntity> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder().userName("Alice").point(0L).build());

        products.add(productRepository.save(ProductEntity.builder().productName("Laptop").unitPrice(1500000L).stock(10L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Monitor").unitPrice(300000L).stock(15L).build()));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 성공")
    void getPoint() {
        // Given
        long userId = user.getId();
        List<OrderProduct> OrderProductList = new ArrayList<>();
        OrderProductList.add(new OrderProduct(products.get(0).getId(), 1L));
        OrderProductList.add(new OrderProduct(products.get(1).getId(), 1L));

        // When
        OrderResponse response = orderApplicationService.createOrder(new OrderRequest(userId, OrderProductList));

        // Then
        assertEquals(OrderStatus.PENDING, response.orderStatus());
    }

    @Test
    @DisplayName("주문 실패: 상품 재고 부족")
    void getPoint_fail_insufficient_product() {
        // Given
        long userId = user.getId();
        List<OrderProduct> OrderProductList = new ArrayList<>();
        OrderProductList.add(new OrderProduct(products.get(0).getId(), 20L));
        OrderProductList.add(new OrderProduct(products.get(1).getId(), 20L));

        // When & Then
        assertThrows(ProductException.class, () -> orderApplicationService.createOrder(new OrderRequest(userId, OrderProductList)));
    }
}
