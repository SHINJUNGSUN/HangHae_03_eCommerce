package io.hhplus.ecommerce.product.infrastructure.persistence.entity;

import io.hhplus.ecommerce.common.model.TimeStamped;
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
public class ProductEntity extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Long unitPrice;

    private Long stock;

    @Version
    private long version;

    public static ProductEntity from(Product product) {
        return ProductEntity.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .stock(product.getStock())
                .build();
    }

    public Product toProduct() {
        return Product.builder()
                .productId(this.id)
                .productName(this.productName)
                .unitPrice(this.unitPrice)
                .stock(this.stock)
                .build();
    }
}
