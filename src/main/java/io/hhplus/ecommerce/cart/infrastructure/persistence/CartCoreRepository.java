package io.hhplus.ecommerce.cart.infrastructure.persistence;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.cart.infrastructure.persistence.entity.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartCoreRepository implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public List<Cart> findByUserSeq(long userSeq) {
        return cartJpaRepository.findByUserSeq(userSeq)
                .stream()
                .map(CartEntity::toCart)
                .toList();
    }

    @Override
    public Optional<Cart> findByUserSeqAndProductId(long userSeq, long productId) {
        return cartJpaRepository.findByUserSeqAndProductId(userSeq, productId)
                .map(CartEntity::toCart);
    }

    @Override
    public void save(Cart cart) {
        cartJpaRepository.save(CartEntity.from(cart));
    }

    @Override
    public void delete(Cart cart) {
        cartJpaRepository.delete(CartEntity.from(cart));
    }
}
