package io.hhplus.ecommerce.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory {

    private Long pointHistoryId;

    private Long userSeq;

    private Long amount;

    private TransactionType transactionType;

    public static PointHistory createChargeHistory(long userSeq, long amount) {
        return PointHistory.builder()
                .userSeq(userSeq)
                .amount(amount)
                .transactionType(TransactionType.CHARGE)
                .build();
    }

    public static PointHistory createUseHistory(long userSeq, long amount) {
        return PointHistory.builder()
                .userSeq(userSeq)
                .amount(amount)
                .transactionType(TransactionType.USE)
                .build();
    }
}
