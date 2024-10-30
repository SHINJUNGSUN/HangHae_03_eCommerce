package io.hhplus.ecommerce.product.domain.model;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    private Long productId;

    private String productName;

    private Long unitPrice;

    private Long stock;

    private Long version;

    public static Product notAvailable(long productId) {
        return Product.builder()
                .productId(productId)
                .productName("")
                .unitPrice(0L)
                .stock(0L)
                .build();
    }

    public void reduceStock(long quantity) {

        if(quantity <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_QUANTITY.getMessage());
        }

        if (this.stock < quantity) {
            throw new IllegalArgumentException(ExceptionMessage.INSUFFICIENT_STOCK.getMessage());
        }

        this.stock -= quantity;
    }
}
