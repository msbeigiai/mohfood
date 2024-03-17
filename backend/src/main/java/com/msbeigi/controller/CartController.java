package com.msbeigi.controller;

import com.msbeigi.model.Cart;
import com.msbeigi.model.CartItem;
import com.msbeigi.model.User;
import com.msbeigi.request.AddCartItemRequest;
import com.msbeigi.request.UpdateCartItemRequest;
import com.msbeigi.service.CartService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCartItemRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, jwt);
        return ResponseEntity.created(URI.create("")).body(cartItem);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(request.cartItemId(), request.quantity());
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable("id") Long cartItemId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        cartService.removeCartItem(cartItemId, jwt);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return ResponseEntity.ok(cart);
    }

}





















