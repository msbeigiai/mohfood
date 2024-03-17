package com.msbeigi.request;

public record IngredientRequest(String name,
                                Long categoryId,
                                Long restaurantId) {
}
