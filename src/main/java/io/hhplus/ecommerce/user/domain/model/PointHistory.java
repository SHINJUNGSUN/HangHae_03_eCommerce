package io.hhplus.ecommerce.user.domain.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
