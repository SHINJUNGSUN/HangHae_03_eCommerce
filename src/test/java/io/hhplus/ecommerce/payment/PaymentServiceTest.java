package io.hhplus.ecommerce.payment;

import io.hhplus.ecommerce.payment.application.service.PaymentApplicationService;
import io.hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

public class PaymentServiceTest {

    @InjectMocks
    PaymentApplicationService paymentApplicationService;

    @Mock
    PaymentRepository paymentRepository;

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