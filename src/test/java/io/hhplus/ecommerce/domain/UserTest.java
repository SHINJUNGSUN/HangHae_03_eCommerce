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
        // Given
        user = User.builder()
                .userName("Alice")
                .point(100000L)
                .build();
    }

    @Test
    @DisplayName("포인트 충전 금액이 0 보다 작거나 같은 경우, 실패")
    void chargeBalance_fail_amountIsNegative() {
        // When & Then
        assertThrows(UserException.class, () -> user.chargePoint(-50000L));
    }
}
