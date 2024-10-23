package io.hhplus.ecommerce.order.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderApplicationService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrder(long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    orderRepository.findByOrderId(orderId).forEach(order::addOrderLine);
                    return order;
                });
    }

    @Override
    public Order createOrder(long userSeq, List<OrderLine> orderLines) {

        Order order = orderRepository.save(Order.create(userSeq));

        orderLines.stream()
                .map(orderLine -> {
                    orderLine.setOrderId(order.getOrderId());
                    return orderRepository.save(orderLine);
                })
                .forEach(order::addOrderLine);

        return order;
    }

    @Override
    public void completeOrder(Order order) {

        order.completeOrder();

        orderRepository.save(order);
    }
}
