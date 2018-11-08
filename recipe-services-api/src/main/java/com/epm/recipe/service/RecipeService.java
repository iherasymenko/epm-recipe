package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();

    Optional<Recipe> readRecipe(long id);

    void createRecipe(String title);

    void updateRecipe(long id, String title);

    void deleteRecipe(long id);

    List<Recipe> readAll();
}
