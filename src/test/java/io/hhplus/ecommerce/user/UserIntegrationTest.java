package io.hhplus.ecommerce.user;

import io.hhplus.ecommerce.user.application.service.UserApplicationService;
import io.hhplus.ecommerce.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.user.infrastructure.User;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import io.hhplus.ecommerce.user.domain.exception.UserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserIntegrationTest {

    @Autowired
    UserApplicationService userApplicationService;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder().userName("Alice").point(0L).build());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("잔액 조회 성공")
    void getPoint() {
        // Given
        long userId = user.getId();

        // When
        UserPointResponse response = userApplicationService.getPoint(userId);

        // Then
        assertEquals(user.getPoint(), response.point());
    }

    @Test
    @DisplayName("잔액 충전 성공")
    void chargePoint() {
        // Given
        long userId = user.getId();
        long amount = 100000L;

        // When
        UserPointResponse response = userApplicationService.chargePoint(new UserPointRequest(userId, amount));

        // Then
        assertEquals(amount, response.point());
    }

    @Test
    @DisplayName("잔액 충전 실패: 유효하지 않은 포인트")
    void chargePoint_fail_invalid_amount() {
        // Given
        long userId = user.getId();
        long amount = -100000L;

        // When & Then
        assertThrows(UserException.class, () -> userApplicationService.chargePoint(new UserPointRequest(userId, amount)));
    }
}
