package io.hhplus.ecommerce.product.infrastructure.persistence;

import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductCoreRepository implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Optional<Product> findByProductId(long productId) {
        return productJpaRepository.findById(productId).map(ProductEntity::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(ProductEntity::toProduct)
                .toList();
    }

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(ProductEntity.from(product)).toProduct();
    }
}
