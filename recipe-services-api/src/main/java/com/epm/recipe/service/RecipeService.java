package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;
import java.util.List;

public interface RecipeService {

    Recipe recipeOfTheDay();

    Recipe findOneById(Long id);

    List<Recipe> findAll();

    void create(Recipe recipe);

    Recipe update(Recipe recipe);

    Recipe delete(Recipe recipe);
}
