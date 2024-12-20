package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Cacheable(cacheNames = "products", key = "#productId")
    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @CachePut(cacheNames = "products", key = "#productId")
    public Product reduceProduct(long productId, long quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.PRODUCT_NOT_FOUND.getMessage()));

        product.reduceStock(quantity);

        productRepository.save(product);

        return product;
    }
}