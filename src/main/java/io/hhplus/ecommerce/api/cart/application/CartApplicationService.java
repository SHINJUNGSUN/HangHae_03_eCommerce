package io.hhplus.ecommerce.api.cart.application;

import io.hhplus.ecommerce.api.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.api.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.api.cart.domain.service.CartService;
import io.hhplus.ecommerce.api.product.domain.model.Product;
import io.hhplus.ecommerce.api.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartApplicationService {

    private final CartService cartService;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<CartResponse> getCarts(long userId) {
        return cartService.getCarts(userId)
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    @Transactional
    public List<CartResponse> addCart(CartAddRequest request) {

        Product product = productService.getProduct(request.productId());

        return cartService.addCart(request.userId(), request.quantity(), product)
                .stream()
                .map(CartResponse::from)
                .toList();
    }

    @Transactional
    public List<CartResponse> removeCart(CartRemoveRequest request) {
        return cartService.removeCart(request.userId(), request.productId())
                .stream()
                .map(CartResponse::from)
                .toList();
    }
}
