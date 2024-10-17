package io.hhplus.ecommerce.api.cart.domain.service;

import io.hhplus.ecommerce.api.cart.domain.model.Cart;
import io.hhplus.ecommerce.api.product.domain.model.Product;

import java.util.List;

public interface CartService {
    List<Cart> getCarts(long userId);
    List<Cart> addCart(long userId, long quantity, Product product);
    List<Cart> removeCart(long userId, long productId);
}
