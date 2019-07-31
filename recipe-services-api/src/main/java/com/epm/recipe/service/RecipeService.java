package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    List<Recipe> all();
    Recipe recipeOfTheDay();
    Optional<Recipe> byId(long id);
}
