package io.hhplus.ecommerce.api.cart.domain.service;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.common.enums.CartError;
import io.hhplus.ecommerce.common.exception.CartException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartDomainService implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<Cart> getCarts(long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> addCart(long userId, long quantity, Product product) {

        Cart cart = cartRepository.findByUserIdAndProductId(userId, product.getId())
                .orElseGet(() -> Cart.create(userId, product));

        cart.increaseQuantity(quantity);

        cartRepository.save(cart);

        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> removeCart(long userId, long productId) {

        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new CartException(CartError.CART_NOT_FOUNT));

        cartRepository.delete(cart);

        return cartRepository.findByUserId(userId);
    }
}
