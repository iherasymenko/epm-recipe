package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
    void add(Recipe recipe);
}
