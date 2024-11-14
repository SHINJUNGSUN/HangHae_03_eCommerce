package io.hhplus.ecommerce.payment;

import io.hhplus.ecommerce.common.util.SlackMessageUtil;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.payment.application.PaymentFacade;
import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.payment.domain.model.PaymentStatus;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.user.application.UserFacade;
import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.user.application.service.UserService;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.model.UserPoint;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class PaymentIntegrationTest {

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

    @MockBean
    SlackMessageUtil slackMessageUtil;

    @Autowired
    PaymentFacade paymentFacade;

    @Autowired
    UserFacade userFacade;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    User user;

    Product product;

    Order order;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder()
                        .userId("alice123")
                        .password("1q2w3e4r!!")
                        .userName("Alice")
                        .userPoint(UserPoint.of(0L))
                        .build());

        product = productRepository.save(Product.builder().productName("Mouse").unitPrice(50000L).stock(10L).build());

        order = orderRepository.save(Order.create(user.getUserSeq()));
        OrderLine orderLine = OrderLine.create(2L, product);
        orderLine.setOrderId(order.getOrderId());
        order.addOrderLine(orderLine);

        orderRepository.save(orderLine);
    }

    @Test
    @DisplayName("결제 성공")
    void payment() {
        // Given
        long userSeq = user.getUserSeq();
        long orderId = order.getOrderId();
        long amount = 200000L;

        userFacade.chargePoint(userSeq, new UserPointRequest(amount));

        // When
        PaymentResponse response = paymentFacade.payment(userSeq, new PaymentRequest(orderId));

        // Then
        assertEquals(PaymentStatus.SUCCESS, response.paymentStatus());
    }

    @Test
    @DisplayName("결제 실패: 주문이 존재하지 않는 경우")
    void payment_fail_order_does_not_exist() {
        // Given
        long userSeq = user.getUserSeq();
        long orderId = 0L;

        // When & Then
        assertThrows(IllegalStateException.class, () -> paymentFacade.payment(userSeq, new PaymentRequest(orderId)));
    }

    @Test
    @DisplayName("결제 동시성 테스트: 동일한 사용자가 동시에 동일한 주문에 대해 5회 결제한 경우, 1회만 결제")
    void payment_concurrency() throws InterruptedException {
        // Given
        long userSeq = user.getUserSeq();
        long orderId = order.getOrderId();
        long amount = 200000L;

        userFacade.chargePoint(userSeq, new UserPointRequest(amount));

        int tryCount = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(tryCount);
        CountDownLatch latch = new CountDownLatch(tryCount);

        // When
        for (int i = 0; i < tryCount; i++) {
            executorService.submit(() -> {
                try {
                    paymentFacade.payment(userSeq, new PaymentRequest(orderId));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        UserPointResponse response = userFacade.getPoint(userSeq);

        // Then
        assertEquals(amount - order.totalPrice(), response.point());
    }

    @Test
    @DisplayName("결제 트랜잭션 테스트: 데이터 플랫폼 전달 로직에서 오류 발생한 경우")
    void payment_transaction() {
        // Given
        long userSeq = user.getUserSeq();
        long orderId = order.getOrderId();
        long amount = 200000L;

        userFacade.chargePoint(userSeq, new UserPointRequest(amount));

        doThrow(new IllegalStateException()).when(slackMessageUtil).sendMessage(anyString());

        // When
        paymentFacade.payment(userSeq, new PaymentRequest(orderId));

        UserPointResponse response = userFacade.getPoint(userSeq);

        // Then
        assertEquals(amount - order.totalPrice(), response.point());
    }
}