package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe recipeOfTheDay();
    Recipe byId(long id);
    List<Recipe> all();
    Recipe add(String title);
    Recipe update(Recipe recipe);
    boolean delete(long id);
}
