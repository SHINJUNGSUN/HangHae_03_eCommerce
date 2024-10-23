package io.hhplus.ecommerce.product.application.service;

import io.hhplus.ecommerce.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductService {

    private final ProductRepository productRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public Optional<Product> getProduct(long productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<ProductResponse> getProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public List<ProductResponse> getPopularProducts() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateTime = now.minusDays(3).toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = now.minusDays(1).toLocalDate().atTime(LocalTime.MAX);

        return orderLineRepository.findPopularProducts(startDateTime, endDateTime)
                .stream()
                .map(productId -> productRepository.findByProductId(productId).orElse(null))
                .filter(Objects::nonNull)
                .map(ProductResponse::from)
                .toList();
    }
}