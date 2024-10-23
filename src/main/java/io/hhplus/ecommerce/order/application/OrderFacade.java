package io.hhplus.ecommerce.order.application;

import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.order.application.service.OrderService;
import io.hhplus.ecommerce.order.domain.exception.OrderException;
import io.hhplus.ecommerce.order.domain.exception.OrderExceptionType;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderResponse createOrder(OrderRequest request) {

        return OrderResponse.from(orderService.createOrder(request.userSeq(),
                request.OrderProductList().stream()
                        .map(orderProduct -> {
                            Product product = productService.getProduct(orderProduct.productId())
                                    .orElseThrow(() ->new OrderException(OrderExceptionType.PRODUCT_NOT_FOUND));

                            if(product.getStock() < orderProduct.quantity()) {
                                throw new OrderException(OrderExceptionType.INSUFFICIENT_STOCK);
                            }

                            return OrderLine.create(orderProduct.quantity(), product);
                        })
                        .toList()));
    }
}
