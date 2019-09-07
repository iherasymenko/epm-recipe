package com.epm.recipe.persistence;

import com.epm.recipe.domain.recipe.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
}
