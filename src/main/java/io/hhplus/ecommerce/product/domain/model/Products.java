package io.hhplus.ecommerce.product.domain.model;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products {

    private List<Product> products;

    public static Products from(List<Product> products) {
        return new Products(products);
    }
}
