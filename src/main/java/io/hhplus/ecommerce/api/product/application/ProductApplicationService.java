package io.hhplus.ecommerce.api.product.application;

import io.hhplus.ecommerce.api.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.api.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApplicationService implements ProductUseCase{

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> products() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Override
    public List<ProductResponse> popularProducts() {
        return List.of();
    }
}
