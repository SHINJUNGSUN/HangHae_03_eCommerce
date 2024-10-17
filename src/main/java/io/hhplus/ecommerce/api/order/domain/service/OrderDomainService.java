package io.hhplus.ecommerce.api.order.domain.service;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.model.OrderLineRequest;
import io.hhplus.ecommerce.api.order.domain.repository.OrderLineRepository;
import io.hhplus.ecommerce.api.order.domain.repository.OrderRepository;
import io.hhplus.ecommerce.common.enums.OrderError;
import io.hhplus.ecommerce.common.exception.OrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDomainService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public Order createOrder(long userId, List<OrderLineRequest> orderLineRequestList) {

        Order order = Order.create(userId);

        for (OrderLineRequest orderLineRequest : orderLineRequestList) {
            order.addOrderLine(orderLineRequest);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Long> popularProducts() {
        return orderLineRepository.popularProducts(LocalDateTime.now().minusDays(3));
    }

    @Override
    public Order order(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));
    }

    @Override
    public void completeOrder(Order order) {
        order.completeOrder();
        orderRepository.save(order);
    }
}
