package io.hhplus.ecommerce.api.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "point_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDateTime createdAt;

    private PointHistory(long userId, long amount) {
        this.userId = userId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public static PointHistory createChargeHistory(long userId, long amount) {
        PointHistory chargeHistory = new PointHistory(userId, amount);
        chargeHistory.transactionType = TransactionType.CHARGE;
        return chargeHistory;
    }

    public static PointHistory createUseHistory(long userId, long amount) {
        PointHistory useHistory = new PointHistory(userId, amount);
        useHistory.transactionType = TransactionType.USE;
        return useHistory;
    }
}
