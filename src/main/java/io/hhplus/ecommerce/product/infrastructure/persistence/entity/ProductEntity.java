package io.hhplus.ecommerce.product.infrastructure.persistence.entity;

import io.hhplus.ecommerce.product.domain.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long unitPrice;

    private Long stock;

    public static ProductEntity of(Product product) {
        return ProductEntity.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .stock(product.getStock())
                .build();
    }

    public Product toDomain() {
        return Product.builder()
                .productId(this.id)
                .productName(this.productName)
                .unitPrice(this.unitPrice)
                .stock(this.stock)
                .build();
    }

//    public void reduceProduct(long quantity) {
//
//        if(quantity <= 0) {
//            throw new ProductException(ProductError.INVALID_QUANTITY);
//        }
//
//        if(this.stock < quantity) {
//            throw new ProductException(ProductError.INSUFFICIENT_STOCK);
//        }
//
//        this.stock -= quantity;
//    }
}
