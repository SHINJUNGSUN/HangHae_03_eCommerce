package io.hhplus.ecommerce.user.application;

public class UserDto {
    public record UserBalanceResponse (
            String userTsid,
            long balance
    ) {}

    public record BalanceChargeRequest (
            String userTsid,
            long amount
    ) {}
}
