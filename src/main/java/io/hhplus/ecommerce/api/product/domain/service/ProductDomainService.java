package io.hhplus.ecommerce.api.product.domain.service;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.repository.ProductRepository;
import io.hhplus.ecommerce.common.enums.ProductError;
import io.hhplus.ecommerce.common.exception.ProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDomainService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(long productId) {
        return productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product outboundProduct(long productId, long quantity) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));

        product.outboundProduct(quantity);

        return productRepository.save(product);
    }
}
