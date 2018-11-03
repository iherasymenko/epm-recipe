package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    List<Recipe> findAll();
    void add(Recipe recipe);
    Optional<Recipe> getById(long id);
    void deleteById(long id);
    void update(Recipe recipe);
}
