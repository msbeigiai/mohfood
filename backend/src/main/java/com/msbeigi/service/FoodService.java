package com.msbeigi.service;

import com.msbeigi.model.Category;
import com.msbeigi.model.Food;
import com.msbeigi.model.Restaurant;
import com.msbeigi.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    Food createFood(CreateFoodRequest foodRequest, Category category, Restaurant restaurant);
    Food deleteFood(Long foodId) throws Exception;
    List<Food> getRestaurantsFood(Long restaurantId,
                                  Boolean isVegetarian,
                                  Boolean isSeasonal,
                                  String foodCategory,
                                  Boolean isNonVeg);
    List<Food> searchFood(String keyword);
    Food findFoodById(Long foodId) throws Exception;
    Food updateAvailability(Long foodId) throws Exception;
}
