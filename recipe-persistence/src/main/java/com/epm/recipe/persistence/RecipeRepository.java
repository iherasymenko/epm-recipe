package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    boolean createRecipe(Recipe recipe);

    List<Recipe> findAll();

    boolean updateRecipe(Recipe recipe);

    boolean deleteRecipe(long id);
}
