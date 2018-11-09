package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();
    List<Recipe> findAll();
    Optional<Recipe> findById(long id);
    void create(Recipe recipe);
    void update(Recipe recipe);
    void delete(long id);

}
