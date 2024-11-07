package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.model.Products;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    @Cacheable(cacheNames = "products", key = "'allProducts'")
    public Products getProducts() {
        return Products.from(productRepository.findAll());
    }

    @Override
    public Product reduceProduct(long productId, long quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.PRODUCT_NOT_FOUND.getMessage()));

        product.reduceStock(quantity);

        return productRepository.save(product);
    }

    @Override
    public void saveProducts(long count) {

        for(long i = 0; i < count; i++) {

            String productName = "Product" + (i + 1);
            long unitPrice = (i + 1) * 10000;
            long stock = (i + 1) * 10;

            Product product = Product.create(productName, unitPrice, stock);

            productRepository.save(product);
        }
    }
}