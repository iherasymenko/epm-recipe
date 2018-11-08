package com.epm.recipe.persistence.mariaDB;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MariaDBRecipeRepository implements RecipeRepository {
    private Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in =
                     MariaDBRecipeRepository.class.getClassLoader().getResourceAsStream("db.properties")) {
            props.load(in);
        }
        String drivers = props.getProperty("db.driver");
        if (drivers != null) {
            System.setProperty("db.driver", drivers);
        }
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.user");
        String password = props.getProperty("");
        return DriverManager.getConnection(url, username, password);
    }

    private final String CREATE_RECIPE_QUERY = "INSERT INTO recipes(title) VALUES (?)";
    @Override
    public Recipe create(Recipe recipe) {
        try (Connection conn = getConnection();
             PreparedStatement stmnt = conn.prepareStatement(CREATE_RECIPE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmnt.setString(1, recipe.getTitle());
            stmnt.executeUpdate();
            ResultSet generatedKeys = stmnt.getGeneratedKeys();
            if (generatedKeys.next()) {
                recipe.setId(generatedKeys.getLong(1));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to insert recipe into DB", e);
        }
        return recipe;
    }

    private static final String FIND_RECIPE_BY_ID_QUERY = "SELECT * FROM recipes WHERE id=?";
    @Override
    public Recipe find(long id) {
        Recipe recipe = null;
        try (Connection conn = getConnection();
             PreparedStatement stmnt = conn.prepareStatement(FIND_RECIPE_BY_ID_QUERY)) {
            stmnt.setLong(1, id);
            ResultSet resultSet = stmnt.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                recipe = new Recipe(id, title);
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to read recipe from DB", e);
        }
        if (recipe == null) {
            recipe = new Recipe(0, "");
        }
        return recipe;
    }

    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipes";
    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipies = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmnt = conn.createStatement()) {
            ResultSet rs = stmnt.executeQuery(FIND_ALL_RECIPES_QUERY);
            while (rs.next()) {
                long id = rs.getLong(1);
                String title = rs.getString(2);
                recipies.add(new Recipe(id, title));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to read recipes from DB", e);
        }
        return recipies;
    }

    private final String UPDATE_RECIPE_QUERY = "UPDATE recipes SET title=? WHERE id=?";
    @Override
    public Recipe update(Recipe recipe) {
        try (Connection conn = getConnection();
             PreparedStatement stmnt = conn.prepareStatement(UPDATE_RECIPE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmnt.setString(1, recipe.getTitle());
            stmnt.setLong(2, recipe.getId());
            stmnt.executeUpdate();
            ResultSet generatedKeys = stmnt.getGeneratedKeys();
            if (generatedKeys.next()) {
                recipe.setTitle(generatedKeys.getString(2));
            }
        } catch (Exception e) {
            throw new ApplicationException("Failed to update recipe in DB", e);
        }
        return recipe;
    }

    private final String DELETE_RECIPE_QUERY = "DELETE FROM recipes WHERE id=?";
    @Override
    public boolean delete(long id) {
        int rowCount;
        try (Connection conn = getConnection();
             PreparedStatement stmnt = conn.prepareStatement(DELETE_RECIPE_QUERY)) {
            stmnt.setLong(1, id);
            rowCount = stmnt.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationException("Failed to delete recipe from DB", e);
        }
        return rowCount > 0;
    }

    class ApplicationException extends RuntimeException {
        ApplicationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
