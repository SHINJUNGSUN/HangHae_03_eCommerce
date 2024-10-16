package io.hhplus.ecommerce.domain;

import io.hhplus.ecommerce.api.user.domain.User;
import io.hhplus.ecommerce.common.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .userName("Alice")
                .point(100000L)
                .build();
    }

    @Test
    @DisplayName("포인트 충전 성공")
    void chargePoint_success() {
        // Given
        long chargeAmount = 50000L;

        // When
        user.chargePoint(chargeAmount);

        // Then
        assertEquals(150000L, user.getPoint());
    }

    @Test
    @DisplayName("포인트 충전 금액이 0 보다 작거나 같은 경우, 실패")
    void chargePoint_fail_amount_is_zero_or_negative() {
        // Given
        long chargeAmount = -50000L;

        // When & Then
        assertThrows(UserException.class, () -> user.chargePoint(chargeAmount));
    }

    @Test
    @DisplayName("포인트 사용 성공")
    void usePoint_success() {
        // Given
        long chargeAmount = 50000L;

        // When
        user.usePoint(chargeAmount);

        // Then
        assertEquals(50000L, user.getPoint());
    }

    @Test
    @DisplayName("포인트 사용 금액이 0 보다 작거나 같은 경우, 실패")
    void usePoint_fail_amount_is_zero_or_negative() {
        // Given
        long useAmount = -50000L;

        // When & Then
        assertThrows(UserException.class, () -> user.usePoint(useAmount));
    }

    @Test
    @DisplayName("포인트 사용 금액이 잔액보다 큰 경우, 실패")
    void usePoint_fail_amount_exceeds_balance() {
        // Given
        long useAmount = 200000L;

        // When & Then
        assertThrows(UserException.class, () -> user.usePoint(useAmount));
    }
}
