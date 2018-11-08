package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class InMemoryRecipeRepository implements RecipeRepository {

    private String propertyfile = "liquibase.properties";
    private String dbURL;
    private String username;
    private String password;

    @Override
    public List<Recipe> findAll() {
        return List.of(
                new Recipe("Hashbrowns", 10),
                new Recipe("Sandwich", 20)
        );
    }


    private Connection getConnection() {

        Properties properties = new Properties();

        try {

            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyfile)) {
                if (inputStream != null) {
                    properties.load(inputStream);
                }
            }

            dbURL = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");


        } catch (IOException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {

            connection = DriverManager
                    .getConnection(dbURL, username, password);
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> recipesList = new ArrayList<>();
        Recipe recipe;
        PreparedStatement preparedStatement;

        try (Connection connection = getConnection()) {

            if (connection != null) {
                String sql = "SELECT * FROM recipes";
                preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    recipe = new Recipe(resultSet.getString("recipe"), resultSet.getLong("id"));
                    recipesList.add(recipe);
                    preparedStatement.executeUpdate();
                }

            }
        }
        return recipesList;
    }

    @Override
    public Recipe getRecipe(Long id) {
        Long recipeId = Objects.requireNonNull(id);
        Recipe recipe = null;
        PreparedStatement preparedStatement = null;
        try (Connection connection = getConnection()) {


            if (connection != null) {

                String sql = "SELECT * FROM recipes WHERE ID = ?";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, recipeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    recipe = new Recipe(resultSet.getString("recipe"), resultSet.getLong("id"));

                    preparedStatement.executeUpdate();
                }

            }


        } catch (SQLException e) {
            System.err.print("No connection!" + e);
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.print("Can't close preparedStatement!" + e);
                e.printStackTrace();
            }
        }
        return recipe;

    }

    private boolean checkData(String title) {
        boolean isExist = false;
        PreparedStatement preparedStatement = null;
        try (Connection connection = getConnection()) {

            String sql = "SELECT true FROM recipes WHERE recipe = ? LIMIT 1;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                isExist = true;
            }

            rs.close();
        } catch (SQLException e) {
            System.err.print("No connection!" + e);
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(preparedStatement).close();

            } catch (SQLException e) {
                System.err.print("Can't close preparedStatement!" + e);
                e.printStackTrace();
            }
        }
        return isExist;
    }

    @Override
    public long Create(Recipe r) {
        Recipe recipe = Objects.requireNonNull(r);
        PreparedStatement preparedStatement = null;
        long id = 0;
        String title = recipe.getTitle();
        if (!checkData(title)) {
            try (Connection connection = getConnection()) {


                String sql = "INSERT INTO recipes (recipe) VALUES(?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, recipe.getTitle());
                preparedStatement.executeUpdate();

                String sqlId = "SELECT id FROM recipes WHERE recipe = ?";
                preparedStatement = connection.prepareStatement(sqlId);
                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    id = resultSet.getLong("id");
                }

            } catch (SQLException e) {
                System.err.print("No connection!" + e);
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(preparedStatement).close();
                } catch (SQLException e) {
                    System.err.print("Can't close preparedStatement!" + e);
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

    @Override
    public void Update(Recipe r, long id) {
        long recipeId = Objects.requireNonNull(id);
        Recipe recipe = Objects.requireNonNull(r);
        PreparedStatement preparedStatement = null;
        String title = recipe.getTitle();
        if (!checkData(title)) {
            try (Connection connection = getConnection()) {


                String sql = "UPDATE recipes SET recipe=? WHERE id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, recipe.getTitle());
                preparedStatement.setLong(2, recipeId);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.err.print("No connection!" + e);
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(preparedStatement).close();
                } catch (SQLException e) {
                    System.err.print("Can't close preparedStatement!" + e);
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void Delete(long id) {
        long recipeId = Objects.requireNonNull(id);
        PreparedStatement preparedStatement = null;
        try (Connection connection = getConnection()) {


            String sql = "DELETE FROM recipes WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, recipeId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.print("No connection!" + e);
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(preparedStatement).close();
            } catch (SQLException e) {
                System.err.print("Can't close preparedStatement!" + e);
                e.printStackTrace();
            }
        }
    }
}