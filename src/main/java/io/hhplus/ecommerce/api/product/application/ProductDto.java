package io.hhplus.ecommerce.api.product.application;

import lombok.Builder;

public class ProductDto {

    @Builder
    public record ProductResponse(
            String productTsid,
            String productName,
            int unitPrice,
            int stock
    ) {
        public static ProductResponse of(String productTsid, String productName, int unitPrice, int stock) {
            return ProductResponse.builder()
                    .productTsid(productTsid)
                    .productName(productName)
                    .unitPrice(unitPrice)
                    .stock(stock)
                    .build();
        }
    }
}
