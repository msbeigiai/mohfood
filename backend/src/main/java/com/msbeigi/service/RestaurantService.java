package com.msbeigi.service;

import com.msbeigi.dto.RestaurantDto;
import com.msbeigi.model.Restaurant;
import com.msbeigi.model.User;
import com.msbeigi.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurantRequest) throws Exception;
    void deleteRestaurant(Long restaurantId) throws Exception;
    List<Restaurant> getAllRestaurants();
    List<Restaurant> searchRestaurant(String keyword);
    Restaurant findRestaurantById(Long restaurantId) throws Exception;
    Restaurant getRestaurantByUserId(Long userId) throws Exception;
    RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
    Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
