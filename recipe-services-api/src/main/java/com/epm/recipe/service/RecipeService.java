package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe recipeOfTheDay();
    List<Recipe> findAll();
    Recipe findById(long id);
    void create(Recipe recipe);
    void update(Recipe recipe);
    void delete(Recipe recipe);
}
