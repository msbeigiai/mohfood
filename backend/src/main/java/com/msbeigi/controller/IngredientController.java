package com.msbeigi.controller;

import com.msbeigi.model.IngredientCategory;
import com.msbeigi.model.IngredientItem;
import com.msbeigi.request.IngredientCategoryRequest;
import com.msbeigi.request.IngredientRequest;
import com.msbeigi.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategory ingredientCategory =
                ingredientService.createIngredientCategory(request.name(), request.restaurantId());
        return ResponseEntity.created(URI.create(""))
                .body(ingredientCategory);
    }

    @PostMapping
    public ResponseEntity<IngredientItem> createIngredientItem(
            @RequestBody IngredientRequest request) throws Exception {
        IngredientItem ingredientItem =
                ingredientService.createIngredientItem(request.restaurantId(), request.name(), request.categoryId());
        return ResponseEntity.created(URI.create(""))
                .body(ingredientItem);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable Long id) throws Exception {
        IngredientItem ingredientItem =
                ingredientService.updateStock(id);
        return ResponseEntity.ok(ingredientItem);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredients(
            @PathVariable Long id) {
        List<IngredientItem> restaurantIngredients = ingredientService.findRestaurantIngredients(id);
        return ResponseEntity.ok(restaurantIngredients);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientsCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientCategory> ingredientCategoryByRestaurantId =
                ingredientService.findIngredientCategoryByRestaurantId(id);
        return ResponseEntity.ok(ingredientCategoryByRestaurantId);
    }
}
