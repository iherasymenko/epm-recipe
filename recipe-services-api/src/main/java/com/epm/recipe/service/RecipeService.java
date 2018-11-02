package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();
    Optional<Recipe> byId(long id);
}
