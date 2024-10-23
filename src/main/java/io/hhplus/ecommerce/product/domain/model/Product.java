package io.hhplus.ecommerce.product.domain.model;

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
}
