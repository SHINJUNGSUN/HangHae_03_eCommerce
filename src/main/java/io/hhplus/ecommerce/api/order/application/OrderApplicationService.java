package io.hhplus.ecommerce.api.order.application;

import io.hhplus.ecommerce.api.order.application.dto.OrderProduct;
import io.hhplus.ecommerce.api.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.api.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.api.order.domain.model.OrderLineRequest;
import io.hhplus.ecommerce.api.order.domain.service.OrderService;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderService orderService;
    private final ProductService productService;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        List<OrderLineRequest> orderLineRequestList = new ArrayList<>();

        for (OrderProduct orderProduct : orderRequest.OrderProductList()) {
            Product product = productService.outboundProduct(orderProduct.productId(), orderProduct.quantity());

            orderLineRequestList.add(OrderLineRequest.of(product, orderProduct.quantity()));
        }

        return OrderResponse.from(orderService.createOrder(orderRequest.userId(), orderLineRequestList));
    }
}
