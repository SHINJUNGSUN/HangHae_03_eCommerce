package io.hhplus.ecommerce.user;

import io.hhplus.ecommerce.user.domain.exception.UserException;
import io.hhplus.ecommerce.user.domain.model.UserPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserPointTest {

    UserPoint userPoint;

    @BeforeEach
    public void setUp() {
        userPoint = UserPoint.builder()
                .point(1L)
                .build();
    }

    @Test
    @DisplayName("포인트 충전 성공")
    void chargePoint_success() {
        // Given
        long chargeAmount = 1L;

        // When
        userPoint.chargePoint(chargeAmount);

        // Then
        assertEquals(2L, userPoint.getPoint());
    }

    @Test
    @DisplayName("포인트 충전 금액이 0 보다 작거나 같은 경우, 포인트 충전 실패")
    void chargePoint_fail_amount_is_zero_or_negative() {
        // Given
        long chargeAmount = -1L;

        // When & Then
        assertThrows(UserException.class, () -> userPoint.chargePoint(chargeAmount));
    }

    @Test
    @DisplayName("포인트 사용 성공")
    void usePoint_success() {
        // Given
        long chargeAmount = 1L;

        // When
        userPoint.usePoint(chargeAmount);

        // Then
        assertEquals(1L, userPoint.getPoint());
    }

    @Test
    @DisplayName("포인트 충전 실패: 포인트 사용 금액이 0 보다 작거나 같은 경우")
    void usePoint_fail_amount_is_zero_or_negative() {
        // Given
        long useAmount = -1L;

        // When & Then
        assertThrows(UserException.class, () -> userPoint.usePoint(useAmount));
    }

    @Test
    @DisplayName("포인트 충전 실패: 포인트 사용 금액이 잔액보다 큰 경우")
    void usePoint_fail_amount_exceeds_balance() {
        // Given
        long useAmount = 2L;

        // When & Then
        assertThrows(UserException.class, () -> userPoint.usePoint(useAmount));
    }
}
