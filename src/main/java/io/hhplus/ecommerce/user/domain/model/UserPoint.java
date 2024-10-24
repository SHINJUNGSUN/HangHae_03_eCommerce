package io.hhplus.ecommerce.user.domain.model;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPoint {

    private Long point;

    public void chargePoint(long amount) {

        if(amount <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }

        this.point += amount;
    }

    public void usePoint(long amount) {

        if(amount <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_AMOUNT.getMessage());
        }

        if(this.point < amount) {
            throw new IllegalArgumentException(ExceptionMessage.INSUFFICIENT_POINT.getMessage());
        }

        this.point -= amount;
    }
}
