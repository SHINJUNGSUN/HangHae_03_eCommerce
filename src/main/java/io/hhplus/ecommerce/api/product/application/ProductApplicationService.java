package io.hhplus.ecommerce.api.product.application;

import io.hhplus.ecommerce.api.order.domain.service.OrderService;
import io.hhplus.ecommerce.api.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.api.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductService productService;
    private final OrderService orderService;

    @Transactional(readOnly = true)
    public List<ProductResponse> products() {
        return productService.getProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> popularProducts() {

        return orderService.popularProducts()
                .stream()
                .map(productId -> {
                    try {
                        return productService.getProduct(productId);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(ProductResponse::from)
                .toList();
    }
}
