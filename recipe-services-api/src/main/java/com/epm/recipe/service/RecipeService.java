package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RecipeService {

    Recipe recipeOfTheDay();

    Optional<Recipe> byId(long id);

    List<Recipe> allInDB() throws SQLException;

    Recipe byIdInDB(long id) throws SQLException;

    long Create(Recipe recipe);

    void Update(Recipe recipe, long id);

    void Delete(long id);
}
