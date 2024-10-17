package io.hhplus.ecommerce.api.order.domain.service;

import io.hhplus.ecommerce.api.order.domain.model.Order;
import io.hhplus.ecommerce.api.order.domain.model.OrderLineRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(long userId, List<OrderLineRequest> orderLineRequestList);
    List<Long> popularProducts();
    Order order(long orderId);
    void completeOrder(Order order);
}
