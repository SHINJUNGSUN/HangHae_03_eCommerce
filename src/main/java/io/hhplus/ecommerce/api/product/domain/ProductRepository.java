package io.hhplus.ecommerce.api.product.domain;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
