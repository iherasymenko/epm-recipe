package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_memory.exception.RecipeInMemoryException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    private JdbcTemplate jdbcTemplate;

    public InMemoryRecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipe = jdbcTemplate.query(
                DatabaseQuery.GET_ALL_RECIPES.query(), (rs, rowNum) -> new Recipe(
                        rs.getString("title"), rs.getLong("id")));
        if (recipe.isEmpty()) { throw new RecipeInMemoryException("No recipes"); }
        return recipe;
    }

    @Override
    public Recipe findById(long id) {
        List<Recipe> recipe = jdbcTemplate.query(DatabaseQuery.GET_BY_ID_RECIPE.query(),
                (rs, rowNum) -> new Recipe(
                        rs.getString("title"), rs.getLong("id")), id);
        if (recipe.isEmpty()) {
            throw new RecipeInMemoryException("Unfortunately, no recipe with id = " + id);
        }
        return recipe.get(0);
    }

    private void checkRecipePresentById(long id) {
        findById(id);
    }

    @Override
    public void create(Recipe recipe) {
        jdbcTemplate.update(DatabaseQuery.CREATE_RECIPE.query(), recipe.title);
    }

    @Override
    public void delete(long id) {
        checkRecipePresentById(id);
        jdbcTemplate.update(DatabaseQuery.DELETE_RECIPE.query(), id);
    }

    @Override
    public void update(Recipe recipe) {
        checkRecipePresentById(recipe.id);
        jdbcTemplate.update(DatabaseQuery.UPDATE_RECIPE.query(), recipe.title, recipe.id);
    }

}
