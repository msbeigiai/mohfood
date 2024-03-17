package com.msbeigi.service.impl;

import com.msbeigi.model.Category;
import com.msbeigi.model.Food;
import com.msbeigi.model.Restaurant;
import com.msbeigi.repository.FoodRepository;
import com.msbeigi.request.CreateFoodRequest;
import com.msbeigi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest foodRequest, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(foodRequest.description());
        food.setImages(foodRequest.images());
        food.setName(foodRequest.name());
        food.setPrice(foodRequest.price());
        food.setIngredientItems(foodRequest.ingredientItems());
        food.setIsSeasonal(foodRequest.seasonal());
        food.setIsVegetarian(foodRequest.vegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public Food deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
        return food;
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         Boolean isVegetarian,
                                         Boolean isSeasonal,
                                         String foodCategory,
                                         Boolean isNonVeg) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }

        if (isNonVeg) {
            foods = filterByNonVeg(foods, isNonVeg);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }

        if (foodCategory != null && !foodCategory.isEmpty()) {
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, Boolean isSeasonal) {
        return foods.stream().filter(food -> food.getIsSeasonal().equals(isSeasonal))
                .collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, Boolean isNonVeg) {
        return foods.stream().filter(food -> !food.getIsVegetarian())
                .collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, Boolean isVegetarian) {
        return foods.stream().filter(food -> food.getIsVegetarian().equals(isVegetarian))
                .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new Exception("Food not found!"));
    }

    @Override
    public Food updateAvailability(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.getAvailable());
        return foodRepository.save(food);
    }
}
