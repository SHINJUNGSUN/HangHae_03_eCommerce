package io.hhplus.ecommerce.api.order.domain.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderLineRepository {
    List<Long> popularProducts(LocalDateTime dateTime);
}
