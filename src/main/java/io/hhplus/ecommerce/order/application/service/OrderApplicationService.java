package io.hhplus.ecommerce.order.application.service;

import io.hhplus.ecommerce.order.domain.exception.OrderException;
import io.hhplus.ecommerce.order.application.dto.OrderRequest;
import io.hhplus.ecommerce.order.application.dto.OrderResponse;
import io.hhplus.ecommerce.order.domain.exception.OrderExceptionType;
import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderApplicationService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order order = orderRepository.save(Order.create(orderRequest.userId()));

        orderRequest.OrderProductList()
                .stream()
                .map(orderProduct -> {
                    Product product = productRepository.findByProductId(orderProduct.productId())
                            .orElseThrow(() ->new OrderException(OrderExceptionType.PRODUCT_NOT_FOUND));

                    if(product.getStock() < orderProduct.amount()) {
                        throw new OrderException(OrderExceptionType.INSUFFICIENT_STOCK);
                    }

                    return orderLineRepository.save(OrderLine.create(order.getOrderId(), order.getOrderId(), product));
                })
                .forEach(order::addOrderLine);

        return OrderResponse.from(order);
    }
}
