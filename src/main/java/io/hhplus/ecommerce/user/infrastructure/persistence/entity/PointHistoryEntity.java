package io.hhplus.ecommerce.user.infrastructure.persistence.entity;

import io.hhplus.ecommerce.common.model.TimeStamped;
import io.hhplus.ecommerce.user.domain.model.PointHistory;
import io.hhplus.ecommerce.user.domain.model.TransactionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "point_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userSeq;

    private Long amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public static PointHistoryEntity from(PointHistory pointHistory) {
        return PointHistoryEntity.builder()
                .id(pointHistory.getPointHistoryId())
                .userSeq(pointHistory.getUserSeq())
                .amount(pointHistory.getAmount())
                .transactionType(pointHistory.getTransactionType())
                .build();
    }
}
