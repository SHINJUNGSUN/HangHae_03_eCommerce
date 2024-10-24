package io.hhplus.ecommerce.product.domain.model;

import io.hhplus.ecommerce.product.domain.exception.ProductException;
import io.hhplus.ecommerce.product.domain.exception.ProductExceptionType;
import io.hhplus.ecommerce.user.domain.exception.UserException;
import io.hhplus.ecommerce.user.domain.exception.UserExceptionType;
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
            throw new ProductException(ProductExceptionType.INVALID_QUANTITY);
        }

        if (this.stock < quantity) {
            throw new ProductException(ProductExceptionType.INSUFFICIENT_STOCK);
        }

        this.stock -= quantity;
    }
}
