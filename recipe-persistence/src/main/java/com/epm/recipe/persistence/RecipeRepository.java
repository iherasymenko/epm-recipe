package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    List<Recipe> findAllRecipes();

    Optional<Recipe> getRecipeById(Long id);

    void insertRecipe(Recipe recipe);

    void updateRecipe(Long id, Recipe recipe);

    void deleteRecipe(Long id);
}
