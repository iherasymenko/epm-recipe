package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.recipe.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.List;
import java.util.Objects;

public class JdbcRecipeRepository implements RecipeRepository {

    private final NamedParameterJdbcOperations jdbcOperations;

    public JdbcRecipeRepository(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations, "jdbcOperations");
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcOperations.query("select id, title from recipes", (rs, __) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }

}
