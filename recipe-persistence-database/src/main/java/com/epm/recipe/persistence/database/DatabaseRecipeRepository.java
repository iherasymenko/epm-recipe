package com.epm.recipe.persistence.database;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class DatabaseRecipeRepository implements RecipeRepository {

    private final JdbcOperations jdbcOperations;


    public DatabaseRecipeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public List<Recipe> findAll() {
        String sql = "SELECT recipe_title, recipe_id FROM recipes";

        return jdbcOperations.query(sql, (rs, rowNum) -> new Recipe(
                rs.getString("recipe_title"),
                rs.getLong("recipe_id")));
    }
}
