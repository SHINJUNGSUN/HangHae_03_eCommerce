package io.hhplus.ecommerce.order.domain.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum OrderStatus {
    PENDING,
    COMPLETED,
    CANCELED
}
