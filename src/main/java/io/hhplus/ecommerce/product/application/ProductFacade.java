package io.hhplus.ecommerce.product.application;

import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final OrderService orderService;


    public List<ProductResponse> getProducts() {
        return productService.getProducts().getProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

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

    public void saveProducts(long count) {
        productService.saveProducts(count);
    }
}
