package io.hhplus.ecommerce.order;

import io.hhplus.ecommerce.order.application.OrderFacade;
import io.hhplus.ecommerce.order.application.dto.OrderProduct;
import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
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
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class OrderIntegrationTest {

    @Container
    static MySQLContainer<?> mySQLContainer;

    @Container
    static GenericContainer<?> redisContainer;

    static {
        mySQLContainer = new MySQLContainer<>("mysql:8.0");
        redisContainer = new GenericContainer<>("redis:6.2.1").withExposedPorts(6379);
        redisContainer.start();
        System.setProperty("spring.data.redis.host", redisContainer.getHost());
        System.setProperty("spring.data.redis.port", redisContainer.getMappedPort(6379).toString());
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    List<User> users = new ArrayList<>();

    List<Product> products = new ArrayList<>();

    @BeforeEach
    void setUp() {
        users.add(userRepository.save(User.builder().userId("alice123").password("1q2w3e4r!!").userName("Alice").userPoint(UserPoint.of(0L)).build()));
        users.add(userRepository.save(User.builder().userId("bob456").password("password123!").userName("Bob").userPoint(UserPoint.of(5000L)).build()));
        users.add(userRepository.save(User.builder().userId("charlie789").password("charliePass!").userName("Charlie").userPoint(UserPoint.of(10000L)).build()));
        users.add(userRepository.save(User.builder().userId("david321").password("dav1dPass!").userName("David").userPoint(UserPoint.of(2000L)).build()));
        users.add(userRepository.save(User.builder().userId("eve987").password("ev3Secure#").userName("Eve").userPoint(UserPoint.of(7500L)).build()));
        users.add(userRepository.save(User.builder().userId("frank654").password("fr4nkPass#").userName("Frank").userPoint(UserPoint.of(12000L)).build()));

        products.add(productRepository.save(Product.builder().productName("Laptop").unitPrice(1500000L).stock(20L).build()));
        products.add(productRepository.save(Product.builder().productName("Monitor").unitPrice(300000L).stock(15L).build()));
    }

    @Test
    @DisplayName("주문 성공")
    void orderCreate() {
        // Given
        long userSeq = users.get(0).getUserSeq();
        List<OrderProduct> orderProductList = new ArrayList<>();
        orderProductList.add(new OrderProduct(products.get(0).getProductId(), 1L));
        orderProductList.add(new OrderProduct(products.get(1).getProductId(), 1L));

        // When
        OrderResponse response = orderFacade.createOrder(userSeq, new OrderRequest(orderProductList));

        // Then
        assertEquals(OrderStatus.PENDING, response.orderStatus());
        assertEquals(orderProductList.size(), response.productCount());
    }

    @Test
    @DisplayName("주문 실패: 상품 재고 부족")
    void orderCreate_fail_insufficient_product() {
        // Given
        long userSeq = users.get(0).getUserSeq();
        List<OrderProduct> orderProductList = new ArrayList<>();
        orderProductList.add(new OrderProduct(products.get(0).getProductId(), 21L));
        orderProductList.add(new OrderProduct(products.get(1).getProductId(), 16L));

        // When & Then
        assertThrows(IllegalArgumentException.class, () ->  orderFacade.createOrder(userSeq, new OrderRequest(orderProductList)));
    }

    @Test
    @DisplayName("주문 동시성 테스트: 6명이 동시에 한 물건을 주문한 경우")
    void orderCreate_concurrency() throws InterruptedException {
        // Given
        ExecutorService executorService = Executors.newFixedThreadPool(users.size());
        CountDownLatch latch = new CountDownLatch(users.size());

        // When
        for(int i = 0; i < users.size(); i++) {
            int index = i;
            executorService.submit(() -> {

                List<OrderProduct> orderProductList = new ArrayList<>();
                orderProductList.add(new OrderProduct(products.get(0).getProductId(), 2L));

                try {
                    orderFacade.createOrder(users.get(index).getUserSeq(), new OrderRequest(orderProductList));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        // Then
        List<OrderLine> orderLines = orderRepository.findByProductId(products.get(0).getProductId());

        assertEquals(users.size(), orderLines.size());
        assertEquals(12L, orderLines.stream().mapToLong(OrderLine::getQuantity).sum());
    }
}
