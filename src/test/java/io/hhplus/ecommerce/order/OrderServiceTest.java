package io.hhplus.ecommerce.order;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.api.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.api.order.domain.service.OrderDomainService;
import io.hhplus.ecommerce.common.exception.OrderException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderDomainService orderDomainService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderLineRepository orderLineRepository;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("주문 조회 성공 테스트")
    public void testOrderFound() {
        // Given
        long userId = 1L;
        long orderId = 1L;
        Order order = Order.create(userId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        // When
        Order response = orderDomainService.order(orderId);

        // Then
        assertThat(response.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("주문 조회 실패 테스트")
    public void testOrderNotFound() {
        // Given
        long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(OrderException.class, () -> orderDomainService.order(orderId));
    }
}
