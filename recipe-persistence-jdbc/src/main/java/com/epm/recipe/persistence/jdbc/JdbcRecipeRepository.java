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
        String SELECT_ALL_RECIPES = "Select * from recipes";
        return this.jdbcTemplate.query(SELECT_ALL_RECIPES, (rs, __) -> {
            return new Recipe(rs.getString("title"), rs.getLong("id"));
        });
    }
}
