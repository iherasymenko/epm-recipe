package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBRecipeRepository implements RecipeRepository {

    private static final String GET_ALL_RECIPES = "SELECT * FROM recipes";

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_RECIPES);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                long id = resultSet.getInt("id");
                Recipe recipe = new Recipe(title, id);
                recipes.add(recipe);
            }
        } catch (Exception e) {
            System.out.println("Failed to load recipe from DB" + e.getMessage());
        }
        return recipes;
    }

    private static final String GET_RECIPE_BY_ID = "SELECT * FROM recipes where id='%s'";

    @Override
    public Recipe findById(long id) {
        Recipe recipe = null;
        try (Connection connection = DBManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(GET_RECIPE_BY_ID, id));
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                recipe = new Recipe(title, id);
            }
        } catch (Exception e) {
            System.out.println("Failed to load recipe from DB" + e.getMessage());
        }
        if (recipe == null){
            throw new IllegalStateException("Recipe not found");
        }
        return recipe;
    }
}
