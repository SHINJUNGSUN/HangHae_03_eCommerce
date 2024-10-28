package io.hhplus.ecommerce.cart.application.service;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartApplicationService implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<Cart> getCarts(long userSeq) {
        return cartRepository.findByUserSeq(userSeq);
    }

    @Override
    public List<Cart> addCart(long userSeq, long quantity, Product product) {

        Cart cart = cartRepository.findByUserSeqAndProductId(userSeq, product.getProductId())
                .orElseGet(() -> Cart.create(userSeq, product.getProductId()));

        cart.addCart(quantity, product);

        cartRepository.save(cart);

        return cartRepository.findByUserSeq(userSeq);
    }

    @Override
    public List<Cart> removeCart(long userSeq, long productId) {

        Cart cart = cartRepository.findByUserSeqAndProductId(userSeq, productId)
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.CART_NOT_FOUND.getMessage()));

        cartRepository.delete(cart);

        return cartRepository.findByUserSeq(userSeq);
    }
}
