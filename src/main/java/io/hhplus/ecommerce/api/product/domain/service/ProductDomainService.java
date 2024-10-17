package io.hhplus.ecommerce.api.product.domain.service;

import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDomainService implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(long productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
