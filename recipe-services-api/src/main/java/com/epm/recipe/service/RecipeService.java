package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();
    Optional<Recipe> byId(long id);
    boolean createRecipe(Recipe recipe);
    List<Recipe> findAll();
    boolean updateRecipe(Recipe recipe);
    boolean deleteRecipe(long id);
}
