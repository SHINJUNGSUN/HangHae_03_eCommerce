package io.hhplus.ecommerce.api.cart.infrastructure;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.cart.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartCoreRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<Cart> findByUserId(long userId) {
        return cartJpaRepository.findByUserId(userId);
    }

    @Override
    public Optional<Cart> findByUserIdAndProductId(long userId, long productId) {
        return cartJpaRepository.findByUserIdAndProductId(userId, productId);
    }

    @Override
    public Cart save(Cart cart) {
        return cartJpaRepository.save(cart);
    }

    @Override
    public void delete(Cart cart) {
        cartJpaRepository.delete(cart);
    }

    @Override
    public void deleteAll() {
        cartJpaRepository.deleteAll();
    }
}
