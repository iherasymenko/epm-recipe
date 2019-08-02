package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Objects;

public class JdbcRecipeRepository implements RecipeRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcRecipeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations, "jdbcOperations");
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcOperations.query("select id, title from recipe", (rs, __) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }

}
