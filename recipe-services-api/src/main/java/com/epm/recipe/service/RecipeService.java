package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();

    Optional<Recipe> byId(long id);

    Recipe findById(long id);

    List<Recipe> findAll();

    boolean create(Recipe recipe);

    boolean update(Recipe recipe);

    boolean delete(long id);
}
