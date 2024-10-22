package io.hhplus.ecommerce.cart.application;

import io.hhplus.ecommerce.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.cart.application.service.CartService;
import io.hhplus.ecommerce.cart.domain.exception.CartException;
import io.hhplus.ecommerce.cart.domain.exception.CartExceptionType;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacade {

    private final CartService cartService;

    public List<CartResponse> getCarts(long userSeq) {
        return cartService.getCarts(userSeq)
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    public List<CartResponse> addCart(CartAddRequest request) {

        Product product = productRepository.findByProductId(request.productId())
                .orElseThrow(() -> new CartException(CartExceptionType.PRODUCT_NOT_FOUND));

        return cartService.addCart(request.userSeq(), request.productId(), request.quantity())
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    public List<CartResponse> removeCart(CartRemoveRequest request) {
        return cartService.removeCart(request.userSeq(), request.productId())
                .stream()
                .map(CartResponse::from)
                .toList();
    }
}
