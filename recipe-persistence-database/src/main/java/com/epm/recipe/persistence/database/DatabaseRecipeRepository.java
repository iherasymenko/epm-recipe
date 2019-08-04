package com.epm.recipe.persistence.database;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DatabaseRecipeRepository implements RecipeRepository {

    private JdbcTemplate jdbcTemplate;

    public DatabaseRecipeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Recipe> findAll() {
        String sql = "SELECT recipe_title, recipe_id FROM recipes";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Recipe(
                rs.getString("recipe_title"),
                rs.getLong("recipe_id")));
    }
}
