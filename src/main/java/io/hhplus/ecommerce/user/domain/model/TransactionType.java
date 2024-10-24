package io.hhplus.ecommerce.user.domain.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum TransactionType {
    USE,
    CHARGE
}
