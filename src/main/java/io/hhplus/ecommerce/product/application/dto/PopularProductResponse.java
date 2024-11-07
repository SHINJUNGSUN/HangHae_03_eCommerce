package io.hhplus.ecommerce.product.application.dto;

import java.util.List;

public record PopularProductResponse(
        List<ProductResponse> products
) {
    public static PopularProductResponse from(List<ProductResponse> products) {
        return new PopularProductResponse(
                products
        );
    }
}
