package io.hhplus.ecommerce.api.product.infrastructure;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.common.enums.ProductError;
import io.hhplus.ecommerce.common.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCoreRepository implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product findByProductId(long productId) {
        return productJpaRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll();
    }
}
