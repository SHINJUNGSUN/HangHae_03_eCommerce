package io.hhplus.ecommerce.api.user.domain.model;

import io.hhplus.ecommerce.common.enums.UserError;
import io.hhplus.ecommerce.common.exception.UserException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private Long point;

    public void chargePoint(long amount) {

        if(amount <= 0) {
            throw new UserException(UserError.INVALID_AMOUNT);
        }

        this.point += amount;
    }

    public void usePoint(long amount) {

        if(amount <= 0) {
            throw new UserException(UserError.INVALID_AMOUNT);
        }

        if(this.point < amount) {
            throw new UserException(UserError.INSUFFICIENT_BALANCE);
        }

        this.point -= amount;
    }
}
