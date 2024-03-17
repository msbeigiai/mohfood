package com.msbeigi.controller;

import com.msbeigi.dto.RestaurantDto;
import com.msbeigi.model.Restaurant;
import com.msbeigi.model.User;
import com.msbeigi.service.RestaurantService;
import com.msbeigi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("id") Long restaurantId) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }


    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites (
            @RequestHeader("Authorization") String jwt,
            @PathVariable("id") Long restaurantId) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        RestaurantDto restaurantDto = restaurantService.addToFavorites(restaurantId, user);

        return ResponseEntity.ok(restaurantDto);
    }
}


























