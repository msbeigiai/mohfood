package com.msbeigi.service.impl;

import com.msbeigi.model.Cart;
import com.msbeigi.model.CartItem;
import com.msbeigi.model.Food;
import com.msbeigi.model.User;
import com.msbeigi.repository.CartItemRepository;
import com.msbeigi.repository.CartRepository;
import com.msbeigi.request.AddCartItemRequest;
import com.msbeigi.service.CartService;
import com.msbeigi.service.FoodService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(request.foodId());

        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getFood().equals(food)) {
                Integer newQuantity = cartItem.getQuantity() + request.quantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.quantity());
        newCartItem.setIngredients(request.ingredients());
        newCartItem.setTotalPrice(request.quantity() * food.getPrice());

        CartItem saveCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItems().add(saveCartItem);

        return saveCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) throws Exception {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new Exception("Cart item not found!"));

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(quantity * cartItem.getFood().getPrice());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeCartItem(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new Exception("Cart item not found!"));
        cart.getCartItems().remove(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        long total = 0L;
        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return cartRepository.findById(id)
                .orElseThrow(() -> new Exception("Cart not found!"));
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(userId);
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();

        return cartRepository.save(cart);
    }

}
