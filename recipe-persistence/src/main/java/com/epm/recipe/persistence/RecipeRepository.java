package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {
    List<Recipe> findAll();

    Optional<Recipe> read(long id);

    void createRecipe(String title);

    void updateRecipe(long id, String title);

    void deleteRecipe(long id);

}
