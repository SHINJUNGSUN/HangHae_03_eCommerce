package io.hhplus.ecommerce.product.application;

import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.product.application.dto.ProductResponse;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final OrderService orderService;


    @Transactional
    public List<ProductResponse> getProducts() {
        return productService.getProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional
    public List<ProductResponse> getPopularProducts() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);

        return orderService.getPopularProducts(now.minusDays(3), now)
                .stream()
                .map(productId -> productService.getProduct(productId)
                        .orElse(Product.notAvailable(productId)))
                .map(ProductResponse::from)
                .toList();
    }
}
