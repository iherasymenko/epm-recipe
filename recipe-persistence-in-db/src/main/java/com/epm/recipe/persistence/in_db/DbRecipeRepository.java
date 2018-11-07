package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.exception.ApplicationException;
import com.epm.recipe.persistence.RecipeRepository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbRecipeRepository implements RecipeRepository {
    private void validateRecipe(Recipe recipe) {
        if (recipe.getTitle().isEmpty()) {
            throw new ApplicationException("Empty title found");
        }
    }

    private void validateRecipeId(long id) {
        getById(id);
    }

    private void validateRecipeDuplicate(Recipe recipe) {
        List<Recipe> list = findAll();
        if (list.contains(recipe)) {
            throw new ApplicationException("Duplicate recipe found");
        }
    }

    private final String SELECT_QUERY = "SELECT * FROM recipe";
    @Override
    public List<Recipe> findAll() {
        List<Recipe> list = new ArrayList<>();
        try (Connection conn = Service.getConnection();
             Statement stat = conn.createStatement()) {
            try (ResultSet result = stat.executeQuery(SELECT_QUERY)) {
                while (result.next()) {
                    long id = result.getLong("id");
                    String title = result.getString("title");
                    Recipe recipe = new Recipe(id, title);
                    list.add(recipe);
                }
            }
        } catch (SQLException | IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
        return list;
    }

    private final String SELECT_BY_ID_QUERY = "SELECT * FROM recipe WHERE id = ?";
    @Override
    public Recipe getById(long id) {
        Recipe element;
        try (Connection conn = Service.getConnection();
             PreparedStatement stat = conn.prepareStatement(SELECT_BY_ID_QUERY)) {
            stat.setLong(1, id);
            ResultSet result = stat.executeQuery();
            if (result.next()) {
                String title = result.getString("title");
                element = new Recipe(id, title);
            } else {
                throw new ApplicationException("Recipe  by id = " + id + " not found");
            }
        } catch (SQLException | IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
        return element;
    }


    private final String INSERT_QUERY = "INSERT INTO recipe (title) VALUES (?)";
    @Override
    public void insert(Recipe recipe) {
        validateRecipe(recipe);
        validateRecipeDuplicate(recipe);
        try (Connection conn = Service.getConnection();
             PreparedStatement stat = conn.prepareStatement(INSERT_QUERY)) {
            stat.setString(1, recipe.getTitle());
            stat.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }


    private final String UPDATE_QUERY = "UPDATE recipe SET title = ? WHERE id = ?";
    @Override
    public void update(Recipe recipe) {
        validateRecipeId(recipe.getId());
        validateRecipe(recipe);
        try (Connection conn = Service.getConnection();
             PreparedStatement stat = conn.prepareStatement(UPDATE_QUERY)) {
            stat.setString(1, recipe.getTitle());
            stat.setLong(2, recipe.getId());
            stat.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    private final String DELETE_BY_ID_QUERY = "DELETE FROM recipe where id = ?";
    @Override
    public void delete(long id) {
        validateRecipeId(id);
        try (Connection conn = Service.getConnection();
             PreparedStatement stat = conn.prepareStatement(DELETE_BY_ID_QUERY)) {
            stat.setLong(1, id);
            stat.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
