package io.hhplus.ecommerce.order.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;
import io.hhplus.ecommerce.order.domain.model.OrderStatus;
import io.hhplus.ecommerce.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderApplicationService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrder(long orderId, OrderStatus orderStatus) {
        return orderRepository.findByIdAndOrderStatus(orderId, orderStatus)
                .map(order -> {
                    orderRepository.findByOrderId(orderId).forEach(order::addOrderLine);
                    return order;
                });
    }

    @Override
    @Transactional
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
    public void updateOrderStatus(OrderStatus orderStatus, Order order) {

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
    }

    @Override
    @Cacheable(cacheNames = "products", key = "'popularProductsIds'")
    public List<Long> getPopularProducts(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return orderRepository.findPopularProducts(startDateTime, endDateTime);
    }
}
