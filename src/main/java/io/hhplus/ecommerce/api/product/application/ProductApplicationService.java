package io.hhplus.ecommerce.api.product.application;

import io.hhplus.ecommerce.api.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.api.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<ProductResponse> products() {
        return productService.getProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> popularProducts() {
        return List.of();
    }
}
