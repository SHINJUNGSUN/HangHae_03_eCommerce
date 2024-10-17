package io.hhplus.ecommerce.api.cart.domain.repository;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    List<Cart> findByUserId(long userId);
    Optional<Cart> findByUserIdAndProductId(long userId, long productId);
    void save(Cart cart);
    void delete(Cart cart);
}
