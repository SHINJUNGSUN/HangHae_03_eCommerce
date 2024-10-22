package io.hhplus.ecommerce.cart.application.service;

import io.hhplus.ecommerce.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.cart.domain.exception.CartException;
import io.hhplus.ecommerce.cart.domain.exception.CartExceptionType;
import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.cart.domain.repository.CartRepository;
import io.hhplus.ecommerce.product.domain.model.Product;
import io.hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartApplicationService implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getCarts(long userSeq) {
        return cartRepository.findByUserSeq(userSeq);
    }

    @Override
    @Transactional
    public List<Cart> addCart(long userSeq, long quantity, Product product) {

        Cart cart = cartRepository.findByUserSeqAndProductId(request.userSeq(), request.productId())
                .orElseGet(() -> Cart.create(request.userSeq(), product));

        cart.addCart(request.quantity());

        cartRepository.save(cart);

        return cartRepository.findByUserSeq(request.userSeq())
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    @Override
    @Transactional
    public List<CartResponse> removeCart(CartRemoveRequest request) {

        Cart cart = cartRepository.findByUserSeqAndProductId(request.userSeq(), request.productId())
                .orElseThrow(() -> new CartException(CartExceptionType.CART_NOT_FOUND));

        cartRepository.delete(cart);

        return cartRepository.findByUserSeq(request.userSeq())
                .stream()
                .map(CartResponse::from)
                .toList();
    }
}
