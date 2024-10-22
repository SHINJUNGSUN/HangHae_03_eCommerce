package io.hhplus.ecommerce.user.domain.model;

import io.hhplus.ecommerce.user.domain.exception.UserException;
import io.hhplus.ecommerce.user.domain.exception.UserExceptionType;
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
            throw new UserException(UserExceptionType.INVALID_AMOUNT);
        }

        this.point += amount;
    }

    public void usePoint(long amount) {

        if(amount <= 0) {
            throw new UserException(UserExceptionType.INVALID_AMOUNT);
        }

        if(this.point < amount) {
            throw new UserException(UserExceptionType.INSUFFICIENT_BALANCE);
        }

        this.point -= amount;
    }
}
