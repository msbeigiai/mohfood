package com.msbeigi.service;

import com.msbeigi.model.IngredientCategory;
import com.msbeigi.model.IngredientItem;

import java.util.List;

public interface IngredientService {
    IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
    IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
    IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;
    List<IngredientItem> findRestaurantIngredients(Long restaurantId);
    IngredientItem updateStock(Long id) throws Exception;
}
