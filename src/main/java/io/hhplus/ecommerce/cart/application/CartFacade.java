package io.hhplus.ecommerce.cart.application;

import io.hhplus.ecommerce.cart.application.dto.CartAddRequest;
import io.hhplus.ecommerce.cart.application.dto.CartRemoveRequest;
import io.hhplus.ecommerce.cart.application.dto.CartResponse;
import io.hhplus.ecommerce.cart.application.service.CartService;
import io.hhplus.ecommerce.cart.domain.model.CartProduct;
import io.hhplus.ecommerce.common.exception.ExceptionMessage;
import io.hhplus.ecommerce.product.application.service.ProductService;
import io.hhplus.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartFacade {

    private final CartService cartService;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public List<CartResponse> getCarts(long userSeq) {
        return cartService.getCarts(userSeq)
                .stream()
                .map(cart -> CartResponse.of(
                        CartProduct.ofCartAndProduct(cart, productService.getProduct(cart.getProductId())
                                .orElse(Product.notAvailable(cart.getProductId()))))
                ).toList();
    }

    @Transactional
    public List<CartResponse> addCart(CartAddRequest request) {

        Product product = productService.getProduct(request.productId())
                .orElseThrow(() -> new IllegalStateException(ExceptionMessage.PRODUCT_NOT_FOUND.getMessage()));

        return cartService.addCart(request.userSeq(), request.quantity(), product)
                .stream()
                .map(cart -> CartResponse.of(
                        CartProduct.ofCartAndProduct(cart, productService.getProduct(cart.getProductId())
                                .orElse(Product.notAvailable(cart.getProductId()))))
                ).toList();
    }

    @Transactional
    public List<CartResponse> removeCart(CartRemoveRequest request) {
        return cartService.removeCart(request.userSeq(), request.productId())
                .stream()
                .map(cart -> CartResponse.of(
                        CartProduct.ofCartAndProduct(cart, productService.getProduct(cart.getProductId())
                                .orElse(Product.notAvailable(cart.getProductId()))))
                ).toList();
    }
}