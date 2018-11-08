package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeRepository {

    List<Recipe> findAll();

    List<Recipe> getAll() throws SQLException;

    Recipe getRecipe(Long id) throws SQLException;

    long Create(Recipe recipe);

    void Update(Recipe r, long id);

    void Delete(long id);
}
