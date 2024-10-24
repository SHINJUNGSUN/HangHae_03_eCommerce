package io.hhplus.ecommerce.product.application;

import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final OrderService orderService;


    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        return productService.getProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getPopularProducts() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDateTime = now.minusDays(3).toLocalDate().atStartOfDay();
        LocalDateTime endDateTime = now.minusDays(1).toLocalDate().atTime(LocalTime.MAX);

        return orderService.getPopularProducts(startDateTime, endDateTime)
                .stream()
                .map(productId -> productService.getProduct(productId)
                        .orElse(Product.notAvailable(productId)))
                .map(ProductResponse::from)
                .toList();
    }
}