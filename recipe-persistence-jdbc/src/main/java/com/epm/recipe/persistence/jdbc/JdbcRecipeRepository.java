package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.jdbc.core.JdbcOperations;

import javax.sql.DataSource;
import java.util.List;

public class JdbcRecipeRepository implements RecipeRepository {

    private final DataSource ds;
    private final JdbcOperations jdbcTemplate;

    public JdbcRecipeRepository(DataSource ds, JdbcOperations jdbcTemplate) {
        this.ds = ds;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcTemplate.query("SELECT id, title FROM recipes",
                (rs, rowNum) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }
}
