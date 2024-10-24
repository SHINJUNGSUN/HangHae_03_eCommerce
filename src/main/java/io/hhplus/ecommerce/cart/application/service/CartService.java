package io.hhplus.ecommerce.cart.application.service;

import io.hhplus.ecommerce.cart.domain.model.Cart;
import io.hhplus.ecommerce.product.domain.model.Product;

import java.util.List;

public interface CartService {
    List<Cart> getCarts(long userSeq);
    List<Cart> addCart(long userSeq, long quantity, Product product);
    List<Cart> removeCart(long userSeq, long productId);
}
