package io.hhplus.ecommerce.user;

import io.hhplus.ecommerce.api.user.domain.model.User;
import io.hhplus.ecommerce.api.user.domain.repository.PointHistoryRepository;
import io.hhplus.ecommerce.api.user.domain.repository.UserRepository;
import io.hhplus.ecommerce.api.user.domain.service.UserDomainService;
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
    UserDomainService userDomainService;

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
        User response = userDomainService.getPoint(userId);

        // Then
        assertEquals(userId, response.getId());
        assertEquals(user.getUserName(), response.getUserName());
        assertEquals(user.getPoint(), response.getPoint());
    }

    @Test
    @DisplayName("포인트 잔액 충전 성공")
    void chargePoint_success() {
        // Given
        long userId = 1L;
        long amount = 50000L;
        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // When
        User response = userDomainService.chargePoint(userId, amount);

        // Then
        assertEquals(userId, response.getId());
        assertEquals(user.getUserName(), response.getUserName());
        assertEquals(user.getPoint(), response.getPoint());
    }

    @Test
    @DisplayName("포인트 잔액 사용 성공")
    void usePoint_success() {
        // Given
        long userId = 1L;
        long amount = 50000L;
        when(userRepository.findByUserId(userId)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // When
        User response = userDomainService.usePoint(userId, amount);

        // Then
        assertEquals(userId, response.getId());
        assertEquals(user.getUserName(), response.getUserName());
        assertEquals(user.getPoint(), response.getPoint());
    }
}
