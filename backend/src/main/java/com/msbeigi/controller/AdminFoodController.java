package com.msbeigi.controller;

import com.msbeigi.model.Food;
import com.msbeigi.model.Restaurant;
import com.msbeigi.model.User;
import com.msbeigi.request.CreateFoodRequest;
import com.msbeigi.response.MessageResponse;
import com.msbeigi.service.FoodService;
import com.msbeigi.service.RestaurantService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest foodRequest,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(foodRequest.restaurantId());
        Food food = foodService.createFood(foodRequest, foodRequest.category(), restaurant);

        return ResponseEntity.created(URI.create(""))
                .body(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable("id") Long foodId,
                                                      @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.deleteFood(foodId);

        MessageResponse messageResponse = new MessageResponse("Food deleted successfully");

        return ResponseEntity.ok(messageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable("id") Long foodId,
                                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailability(foodId);

        return ResponseEntity.ok(food);
    }

}





























