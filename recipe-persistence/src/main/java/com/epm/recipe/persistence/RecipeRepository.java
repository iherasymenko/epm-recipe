package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();

    boolean add(Recipe recipe);

    Recipe removeById(int id);

    Recipe updateValueById(int id, String value);
}
