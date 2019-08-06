package com.epm.recipe.persistence.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

@Component
public class JdbcRecipeRepository implements RecipeRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcRecipeRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        return this.jdbcTemplate.query("Select * from recipes",
                (rs, __) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }
}
