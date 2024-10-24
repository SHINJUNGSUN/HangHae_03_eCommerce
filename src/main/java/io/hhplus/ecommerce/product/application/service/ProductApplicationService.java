package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.product.domain.exception.ProductException;
import io.hhplus.ecommerce.product.domain.exception.ProductExceptionType;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProduct(long productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product reduceProduct(long productId, long quantity) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.PRODUCT_NOT_FOUND));

        product.reduceStock(quantity);

        return productRepository.save(product);
    }
}