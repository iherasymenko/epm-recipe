package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    Recipe create(Recipe recipe);
    Recipe find(long id);
    List<Recipe> findAll();
    Recipe update(Recipe recipe);
    boolean delete(long id);
}
