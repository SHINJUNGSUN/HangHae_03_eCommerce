package io.hhplus.ecommerce.api.product.domain.model;

import io.hhplus.ecommerce.common.enums.ProductError;
import io.hhplus.ecommerce.common.exception.ProductException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long unitPrice;

    private Long stock;

    public void reduceProduct(long quantity) {

        if(quantity <= 0) {
            throw new ProductException(ProductError.INVALID_QUANTITY);
        }

        if(this.stock < quantity) {
            throw new ProductException(ProductError.INSUFFICIENT_STOCK);
        }

        this.stock -= quantity;
    }
}
