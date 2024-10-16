package io.hhplus.ecommerce.application;

import io.hhplus.ecommerce.api.user.application.UserApplicationService;
import io.hhplus.ecommerce.api.user.application.dto.UserPointRequest;
import io.hhplus.ecommerce.api.user.application.dto.UserPointResponse;
import io.hhplus.ecommerce.api.user.domain.PointHistoryRepository;
import io.hhplus.ecommerce.api.user.domain.User;
import io.hhplus.ecommerce.api.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    UserApplicationService userApplicationService;

    @Mock
    UserRepository userRepository;

    @Mock
    PointHistoryRepository pointHistoryRepository;

    User user;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        user = User.builder()
                .id(1L)
                .userName("Alice")
                .point(100000L)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("포인트 잔액 조회 성공")
    void point_success() {
        // Given
        long userId = 1L;
        when(userRepository.findByUserId(userId)).thenReturn(user);

        // When
        UserPointResponse response = userApplicationService.point(userId);

        // Then
        assertEquals(userId, response.userId());
        assertEquals(user.getUserName(), response.userName());
        assertEquals(user.getPoint(), response.point());
    }

    @Test
    @DisplayName("포인트 잔액 충전 성공")
    void chargePoint_success() {
        // Given
        long userId = 1L;
        long amount = 50000L;
        UserPointRequest request = new UserPointRequest(userId, amount);
        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // When
        UserPointResponse response = userApplicationService.chargePoint(request);

        // Then
        assertEquals(userId, response.userId());
        assertEquals(user.getUserName(), response.userName());
        assertEquals(user.getPoint(), response.point());
    }

    @Test
    @DisplayName("포인트 잔액 사용 성공")
    void usePoint_success() {
        // Given
        long userId = 1L;
        long amount = 50000L;
        UserPointRequest request = new UserPointRequest(userId, amount);
        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // When
        UserPointResponse response = userApplicationService.usePoint(request);

        // Then
        assertEquals(userId, response.userId());
        assertEquals(user.getUserName(), response.userName());
        assertEquals(user.getPoint(), response.point());
    }
}
