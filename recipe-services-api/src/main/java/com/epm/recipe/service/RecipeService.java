package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> getAll();

    Recipe recipeOfTheDay();

    Optional<Recipe> getById(long id);
}
