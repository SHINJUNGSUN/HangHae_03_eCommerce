package io.hhplus.ecommerce.order.application.service;

import io.hhplus.ecommerce.order.domain.model.Order;
import io.hhplus.ecommerce.order.domain.model.OrderLine;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrder(long orderId);
    Order createOrder(long userSeq, List<OrderLine> orderLines);
    void completeOrder(Order order);
}
