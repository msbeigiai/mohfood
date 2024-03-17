package com.msbeigi.request;

import com.msbeigi.model.Category;
import com.msbeigi.model.IngredientItem;

import java.util.List;

public record CreateFoodRequest(
        String name,
        String description,
        Long price,
        Category category,
        List<String> images,
        Long restaurantId,
        Boolean vegetarian,
        Boolean seasonal,
        List<IngredientItem> ingredientItems
) {
}
