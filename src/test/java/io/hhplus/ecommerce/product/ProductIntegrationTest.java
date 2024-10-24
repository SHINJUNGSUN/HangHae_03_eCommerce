package io.hhplus.ecommerce.product;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.product.application.ProductFacade;
import io.hhplus.ecommerce.product.application.dto.ProductResponse;
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

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class ProductIntegrationTest {

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
    ProductFacade productFacade;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    User user;

    List<Product> products = new ArrayList<>();

    Order order;

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
        products.add(productRepository.save(Product.builder().productName("Headphones").unitPrice(150000L).stock(50L).build()));

        order = orderRepository.save(Order.create(user.getUserSeq()));

        order.addOrderLine(OrderLine.create(3L, products.get(0)));
        order.addOrderLine(OrderLine.create(2L, products.get(1)));
        order.addOrderLine(OrderLine.create(1L, products.get(2)));

        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrderId(order.getOrderId());
            orderRepository.save(orderLine);
        }
    }

    @Test
    @DisplayName("상품 목록 조회 성공")
    void products() {

        // When
        List<ProductResponse> response = productFacade.getProducts();

        // Then
        assertThat(response)
                .hasSize(3)
                .extracting(i -> tuple(i.productId(), i.ProductName(), i.stock(), i.unitPrice()))
                .containsExactlyElementsOf(
                        products.stream()
                                .map(product -> tuple(product.getProductId(), product.getProductName(), product.getStock(), product.getUnitPrice()))
                                .toList()
                );
    }

    @Test
    @DisplayName("상위 목록 조회 성공")
    void popularProducts() {
        // When
        List<ProductResponse> response = productFacade.getPopularProducts();


        // Then
        assertThat(response)
                .hasSize(3)
                .extracting(ProductResponse::productId)
                .containsExactlyInAnyOrder(products.get(0).getProductId(), products.get(1).getProductId(), products.get(2).getProductId());
    }
}
