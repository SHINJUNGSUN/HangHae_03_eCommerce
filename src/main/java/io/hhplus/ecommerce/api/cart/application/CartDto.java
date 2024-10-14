package io.hhplus.ecommerce.api.cart.application;

public class CartDto {

    public record CartResponse (
            String cartTsid,
            String productTsid,
            String productName,
            int quantity,
            int unit_price
    ) {}

    public record CartAddRequest (
            String userTsid,
            String productTsid,
            int quantity
    ) {}

    public record CartRemoveRequest (
            String userTsid,
            String cartTsid
    ) {}
}
