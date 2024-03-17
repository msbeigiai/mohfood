package com.msbeigi.service;

import com.msbeigi.model.Cart;
import com.msbeigi.model.CartItem;
import com.msbeigi.request.AddCartItemRequest;

public interface CartService {
    CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception;
    CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception;
    Cart removeCartItem(Long cartItemId, String jwt) throws Exception;
    Long calculateCartTotals(Cart cart) throws Exception;
    Cart findCartById(Long id) throws Exception;
    Cart findCartByUserId(Long userId) throws Exception;
    Cart clearCart(Long userId) throws Exception;
}
