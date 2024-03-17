package com.msbeigi.service.impl;

import com.msbeigi.model.IngredientCategory;
import com.msbeigi.model.IngredientItem;
import com.msbeigi.model.Restaurant;
import com.msbeigi.repository.IngredientCategoryRepository;
import com.msbeigi.repository.IngredientItemRepository;
import com.msbeigi.service.IngredientService;
import com.msbeigi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        return ingredientCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingredient category not found!"));
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setName(ingredientName);
        ingredientItem.setRestaurant(restaurant);
        ingredientItem.setCategory(category);

        IngredientItem ingredients = ingredientItemRepository.save(ingredientItem);
        category.getIngredientItems().add(ingredients);

        return ingredients;
    }

    @Override
    public List<IngredientItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        IngredientItem ingredientItem = ingredientItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingredient not found!"));
        ingredientItem.setInStock(!ingredientItem.getInStock());
        return ingredientItemRepository.save(ingredientItem);
    }
}
