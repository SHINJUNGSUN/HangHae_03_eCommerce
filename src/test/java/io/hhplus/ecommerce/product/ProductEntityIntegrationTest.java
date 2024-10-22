package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.product.application.service.ProductApplicationService;
import io.hhplus.ecommerce.product.application.dto.ProductResponse;
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

@SpringBootTest
@Transactional
public class ProductEntityIntegrationTest {

    @Autowired
    ProductApplicationService productApplicationService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    User user;

    List<ProductEntity> products = new ArrayList<>();

    OrderEntity order;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder().userName("Alice").point(0L).build());

        products.add(productRepository.save(ProductEntity.builder().productName("Laptop").unitPrice(1500000L).stock(10L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Smartphone").unitPrice(800000L).stock(20L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Headphones").unitPrice(150000L).stock(50L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Keyboard").unitPrice(50000L).stock(30L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Mouse").unitPrice(30000L).stock(40L).build()));
        products.add(productRepository.save(ProductEntity.builder().productName("Monitor").unitPrice(300000L).stock(15L).build()));

        order = OrderEntity.create(user.getId());
        order.addOrderLine(new OrderLineRequest(products.get(0), 5L));
        order.addOrderLine(new OrderLineRequest(products.get(1), 4L));
        order.addOrderLine(new OrderLineRequest(products.get(2), 3L));
        order.addOrderLine(new OrderLineRequest(products.get(3), 2L));
        order.addOrderLine(new OrderLineRequest(products.get(4), 1L));
        order = orderRepository.save(order);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 목록 조회 성공")
    void products() {
        // When
        List<ProductResponse> response = productApplicationService.products();

        // Then
        assertEquals(products.stream().map(ProductResponse::from).toList(), response);
    }

    @Test
    @DisplayName("상위 목록 조회 성공")
    void popularProducts() {
        // When
        List<ProductResponse> response = productApplicationService.popularProducts();

        // Then
        assertEquals(products.get(0).getId(), response.get(0).productId());
        assertEquals(products.get(1).getId(), response.get(1).productId());
        assertEquals(products.get(2).getId(), response.get(2).productId());
        assertEquals(products.get(3).getId(), response.get(3).productId());
        assertEquals(products.get(4).getId(), response.get(4).productId());
    }
}
