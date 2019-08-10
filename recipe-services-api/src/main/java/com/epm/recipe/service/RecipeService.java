package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> all();
    Recipe recipeOfTheDay();
    Recipe byId(int id);
    List<Recipe> getByRestaurant(String title);
}
