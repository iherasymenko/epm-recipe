package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe recipeOfTheDay();

    List<Recipe> findAll();

    Recipe findRecipeById(long id);

    void createRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void deleteRecipe(Recipe recipe);

}
