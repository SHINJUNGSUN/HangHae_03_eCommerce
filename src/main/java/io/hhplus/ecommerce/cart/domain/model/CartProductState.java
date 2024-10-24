package io.hhplus.ecommerce.cart.domain.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CartProductState {
    AVAILABLE,
    OUT_OF_STOCK
}
