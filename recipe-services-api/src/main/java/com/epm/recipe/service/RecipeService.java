package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();
    Optional<Recipe> getById(long id);
    List<Recipe> getAll();
    void add(Recipe recipe);
    void update(Recipe recipe);
    void deleteById(long id);
}
