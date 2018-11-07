package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe recipeOfTheDay();
    Optional<Recipe> byId(long id);

    List<Recipe> findAll();

    boolean add(int id, String title);

    Recipe removeById(int id);

    Recipe updateValueById(int id, String title);
}
