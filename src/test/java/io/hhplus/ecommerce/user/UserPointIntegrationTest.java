package io.hhplus.ecommerce.user;

import io.hhplus.ecommerce.user.application.UserFacade;
import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class UserPointIntegrationTest {

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
    UserFacade userFacade;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder()
                        .userId("alice123")
                        .password("1q2w3e4r!!")
                        .userName("Alice")
                        .userPoint(UserPoint.of(0L))
                        .build());
    }

    @Test
    @DisplayName("포인트 조회 성공")
    void getPoint() {
        // Given
        long userSeq = user.getUserSeq();

        // When
        UserPointResponse response = userFacade.getPoint(userSeq);

        // Then
        assertEquals(user.getUserPoint().getPoint(), response.point());
    }

    @Test
    @DisplayName("포인트 충전 성공")
    void chargePoint() {
        // Given
        long userSeq = user.getUserSeq();
        long amount = 1L;

        // When
        UserPointResponse response = userFacade.chargePoint(userSeq, new UserPointRequest(amount));

        // Then
        assertEquals(amount, response.point());
    }

    @Test
    @DisplayName("포인트 충전 실패: 유효하지 않은 포인트")
    void chargePoint_fail_invalid_amount() {
        // Given
        long userSeq = user.getUserSeq();
        long amount = -1L;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userFacade.chargePoint(userSeq, new UserPointRequest(amount)));
    }

    @Test
    @DisplayName("포인트 충전 동시성 테스트: 동일한 사용자가 동시에 포인트 충전을 5회 요청한 경우 순차적으로 충전")
    void chargePoint_concurrency() throws InterruptedException {
        // Given
        long userSeq = user.getUserSeq();
        long amount = 1L;

        int tryCount = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(tryCount);
        CountDownLatch latch = new CountDownLatch(tryCount);

        // When
        for (int i = 0; i < tryCount; i++) {
            executorService.submit(() -> {
                try {
                    userFacade.chargePoint(userSeq, new UserPointRequest(amount));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        UserPointResponse response = userFacade.getPoint(userSeq);

        // Then
        assertEquals(amount * tryCount, response.point());
    }
}
