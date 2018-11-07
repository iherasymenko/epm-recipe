package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();

    Recipe findById(long id);

    boolean create(Recipe recipe);

    boolean update(Recipe recipe);

    boolean delete(long id);
}
