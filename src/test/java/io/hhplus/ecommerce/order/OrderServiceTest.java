package io.hhplus.ecommerce.order;

import io.hhplus.ecommerce.order.application.service.OrderApplicationService;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

public class OrderServiceTest {

    @InjectMocks
    OrderApplicationService orderApplicationService;

    @Mock
    OrderRepository orderRepository;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}