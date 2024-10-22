package io.hhplus.ecommerce.cart.domain.repository;

import io.hhplus.ecommerce.cart.domain.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {
    List<Cart> findByUserSeq(long userSeq);
    Optional<Cart> findByUserSeqAndProductId(long userSeq, long productId);
    void save(Cart cart);
    void delete(Cart cart);
}
