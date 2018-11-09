package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.in_db.DBManager;
import com.epm.recipe.service.RecipeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DefaultRecipeService implements RecipeService {

    private static final String GET_ALL_RECIPES = "select * from recipes";

    @Override
    public ArrayList<Recipe> getAllRecipes() {
        Recipe recipe = null;
        ArrayList<Recipe> all = new ArrayList<>();

        try (Connection connection = DBManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_RECIPES);
            while (resultSet.next()) {
                recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setTitle(resultSet.getString("title"));

                all.add(recipe);
            }
        } catch (Exception e) {
            System.out.println("Failed to load recipe from DB" + e.getMessage());
        }
        return all;
    }


    private static final String INSERT_RECIPE = "insert into recipes (id, title) values (? , ?)";

    @Override
    public Recipe addRecipe(Integer id, String title) {
        Recipe recipe = null;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RECIPE)) {
            recipe = new Recipe();
            recipe.setId(id);
            recipe.setTitle(title);

            statement.setInt(1, id);
            statement.setString(2, title);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to insert recipe into DB" + e);
        }
        return recipe;
    }


    private static final String DELETE_RECIPE_BY_ID = "delete from recipes where id = '%s'";
    private static final String GET_DELETED_RECIPE = "select * from recipes where id = '%s'";

    @Override
    public Recipe deleteRecipe(Integer id) {
        Recipe recipe = null;
        try (Connection connection = DBManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(GET_DELETED_RECIPE, id));
            while (resultSet.next()) {
                recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setTitle(resultSet.getString("title"));
            }
            statement.executeUpdate(String.format(DELETE_RECIPE_BY_ID, id));
        } catch (Exception e) {
            System.out.println("Cannot delete recipe!" + e.getMessage());
        }
        return recipe;
    }


    private static final String UPDATE_RECIPE = "update recipes set title = '%s' where id = '%s'";
    private static final String GET_UPDATED_RECIPE = "select * from recipes where id = '%s' and title = '%s'";

    @Override
    public Recipe updateRecipe(Integer id, String title) {
        Recipe recipe = null;

        try (Connection connection = DBManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(UPDATE_RECIPE, title, id));

            ResultSet resultSet = statement.executeQuery(String.format(GET_UPDATED_RECIPE, id, title));
            while (resultSet.next()) {
                recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setTitle(resultSet.getString("title"));
            }
        } catch (Exception e) {
            System.out.println("Cannot update recipe by ID!");
        }
        return recipe;
    }
}
