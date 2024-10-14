package io.hhplus.ecommerce.api.order.application;

import lombok.Builder;

import java.util.List;

public class OrderDto {
    public record OrderRequest (
        String userTsid,
        List<ProductRequest> productList
    ) {}

    @Builder
    public record OrderResponse (
        String orderTsid,
        int totalPrice,
        int status,
        List<ProductResponse> productList
    ) {
        public static OrderResponse of(String orderTsid, int totalPrice, int status, List<ProductResponse> productList) {
            return OrderResponse.builder()
                    .orderTsid(orderTsid)
                    .totalPrice(totalPrice)
                    .status(status)
                    .productList(productList)
                    .build();
        }
    }

    public record ProductRequest (
        String productTsid,
        int quantity
    ) {}

    @Builder
    public record ProductResponse (
        String productTsid,
        String productName,
        int quantity,
        int unitPrice
    ) {
        public static ProductResponse of(String productTsid, String productName, int quantity, int unitPrice) {
            return ProductResponse.builder()
                    .productTsid(productTsid)
                    .productName(productName)
                    .quantity(quantity)
                    .unitPrice(unitPrice)
                    .build();
        }
    }
}
