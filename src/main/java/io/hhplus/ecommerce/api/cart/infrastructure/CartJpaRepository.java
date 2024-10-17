package io.hhplus.ecommerce.api.cart.infrastructure;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(long userId);
    Optional<Cart> findByUserIdAndProductId(long userId, long productId);
}
