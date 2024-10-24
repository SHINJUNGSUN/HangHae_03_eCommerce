package io.hhplus.ecommerce.user;

import io.hhplus.ecommerce.user.application.service.UserPointApplicationService;
import io.hhplus.ecommerce.user.domain.model.User;
import io.hhplus.ecommerce.user.domain.model.UserPoint;
import io.hhplus.ecommerce.user.domain.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserPointServiceTest {

    @InjectMocks
    UserPointApplicationService userPointApplicationService;

    @Mock
    UserRepository userRepository;


    User user;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);

        UserPoint userPoint = UserPoint.builder()
                .point(1L)
                .build();

        user = User.builder()
                .userSeq(1L)
                .userPoint(userPoint)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("포인트 조회 성공")
    void getPoint_success() {
        // Given
        long userSeq = 1L;
        when(userRepository.findById(userSeq)).thenReturn(Optional.of(user));

        // When
        User response = userPointApplicationService.getPoint(userSeq);

        // Then
        assertEquals(user, response);
    }

    @Test
    @DisplayName("포인트 조회 실패: 사용자가 없는 경우")
    void getPoint_fail_UserNotFound() {
        // Given
        long userSeq = 1L;
        when(userRepository.findById(userSeq)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NullPointerException.class, () -> userPointApplicationService.getPoint(userSeq));
    }

    @Test
    @DisplayName("포인트 잔액 충전 성공")
    void chargePoint_success() {
        // Given
        long userSeq = 1L;
        long amount = 1L;
        when(userRepository.findById(userSeq)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // When
        User response = userPointApplicationService.chargePoint(userSeq, amount);

        // Then
        assertEquals(user, response);
    }

    @Test
    @DisplayName("포인트 잔액 사용 성공")
    void usePoint_success() {
        // Given
        long userSeq = 1L;
        long amount = 1L;
        when(userRepository.findById(userSeq)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // When
        User response = userPointApplicationService.usePoint(userSeq, amount);

        // Then
        assertEquals(user, response);
    }
}
