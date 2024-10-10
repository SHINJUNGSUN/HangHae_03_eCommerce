package io.hhplus.ecommerce.cart.application;

import java.util.List;

public class CartDto {

    public record CartResponse (
            String cartTsid,
            String productTsid,
            String productName,
            int quantity,
            int unit_price
    ) {}

    public record CartPatchRequest (
            String cartTsid,
            int quantity
    ) {}
}
