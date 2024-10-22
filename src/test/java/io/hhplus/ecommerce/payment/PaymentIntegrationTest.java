package io.hhplus.ecommerce.payment;

import io.hhplus.ecommerce.order.infrastructure.persistence.entity.OrderEntity;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.payment.application.service.PaymentApplicationService;
import io.hhplus.ecommerce.payment.application.dto.PaymentRequest;
import io.hhplus.ecommerce.payment.application.dto.PaymentResponse;
import io.hhplus.ecommerce.payment.domain.model.PaymentStatus;
import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.user.application.service.UserApplicationService;
import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.infrastructure.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import io.hhplus.ecommerce.order.domain.exception.OrderException;
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
public class PaymentIntegrationTest {

    @Autowired
    PaymentApplicationService paymentApplicationService;

    @Autowired
    UserApplicationService userApplicationService;

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
        products.add(productRepository.save(ProductEntity.builder().productName("Monitor").unitPrice(300000L).stock(15L).build()));

        order = OrderEntity.create(user.getId());
        order.addOrderLine(new OrderLineRequest(products.get(0), 1L));
        order.addOrderLine(new OrderLineRequest(products.get(1), 2L));
        order = orderRepository.save(order);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("결제 성공")
    void payment() {
        // Given
        long userId = user.getId();
        long orderId = order.getId();
        long amount = 3000000L;

        userApplicationService.chargePoint(new UserPointRequest(userId, amount));

        // When
        PaymentResponse response = paymentApplicationService.payment(new PaymentRequest(userId, orderId));

        // Then
        assertEquals(PaymentStatus.SUCCESS, response.paymentStatus());
    }

    @Test
    @DisplayName("결제 실패: 잔액 부족")
    void payment_fail_insufficient_balance() {
        // Given
        long userId = user.getId();
        long orderId = order.getId();

        // When
        PaymentResponse response = paymentApplicationService.payment(new PaymentRequest(userId, orderId));

        // Then
        assertEquals(PaymentStatus.FAILED, response.paymentStatus());
    }

    @Test
    @DisplayName("결제 실패: 주문이 존재하지 않는 경우")
    void payment_fail_order_does_not_exist() {
        // Given
        long userId = user.getId();
        long orderId = 0L;

        // When & Then
        assertThrows(OrderException.class, () -> paymentApplicationService.payment(new PaymentRequest(userId, orderId)));
    }
}
