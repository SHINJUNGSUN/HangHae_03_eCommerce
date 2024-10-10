package io.hhplus.ecommerce.user.application;

import lombok.Builder;

public class UserDto {

    public record BalanceUpdateRequest (
            String userTsid,
            long amount
    ) {}

    @Builder
    public record BalanceResponse (
            String userTsid,
            long balance
    ) {
        public static BalanceResponse of(String userTsid, long balance) {
            return BalanceResponse.builder()
                    .userTsid(userTsid)
                    .balance(balance)
                    .build();
        }
    }
}
