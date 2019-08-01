package com.epm.recipe.persistence.database;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.database.connection.JdbcConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DatabaseRecipeRepository implements RecipeRepository {

    private static final String ALL_RECIPES_QUERY = "SELECT recipe_title, recipe_id FROM recipes";

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = null;
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(ALL_RECIPES_QUERY);
            while (resultSet.next()) {
                String title = resultSet.getString("recipe_title");
                long id = resultSet.getLong("recipe_id");
                recipe = new Recipe(title, id);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load all recipes from db: " + e.getMessage());
        }
        if (recipe == null) {
            throw new NoSuchElementException("Recipe not found in db");
        }
        return recipes;
    }
}
