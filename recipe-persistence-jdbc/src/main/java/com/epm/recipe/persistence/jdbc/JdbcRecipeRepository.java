package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcRecipeRepository implements RecipeRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        return this.jdbcTemplate.query("Select * from recipes",
                (rs, __) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }
}
