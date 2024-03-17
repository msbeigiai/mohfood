package com.msbeigi.controller;

import com.msbeigi.model.Food;
import com.msbeigi.model.Restaurant;
import com.msbeigi.model.User;
import com.msbeigi.request.CreateFoodRequest;
import com.msbeigi.service.FoodService;
import com.msbeigi.service.RestaurantService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(keyword);

        return ResponseEntity.ok(foods);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam Boolean vegetarian,
            @RequestParam Boolean seasonal,
            @RequestParam Boolean nonVeg,
            @RequestParam(name = "food_category", required = false) String foodCategory,
            @PathVariable("id") Long restaurantId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantsFood(
                restaurantId,
                vegetarian,
                seasonal,
                foodCategory,
                nonVeg);

        return ResponseEntity.ok(foods);
    }

}
