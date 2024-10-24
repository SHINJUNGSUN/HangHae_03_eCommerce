package io.hhplus.ecommerce.payment.domain.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum PaymentStatus {
    SUCCESS,
    FAILED,
    CANCELLED,
}
