package io.hhplus.ecommerce.order.application;

import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;

    @Transactional
    public OrderResponse createOrder(long userSeq, OrderRequest request) {

        List<OrderLine> orderLines = request.OrderProductList()
                .stream()
                .map(orderProduct -> {
                    Product product = productService.reduceProduct(orderProduct.productId(), orderProduct.quantity());
                    return OrderLine.create(orderProduct.quantity(), product);
                })
                .toList();

        return OrderResponse.from(orderService.createOrder(userSeq, orderLines));
    }
}
