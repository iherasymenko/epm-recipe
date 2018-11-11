package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.exception.ApplicationException;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DbRecipeRepository2 implements RecipeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

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
        List<Recipe> list;
        try {
            list = jdbcTemplateObject.query(SELECT_QUERY, new RecipeMapper());
        } catch (Throwable e) {
            throw new ApplicationException(e.getMessage(), e);
        }
        return list;
    }

    private final String SELECT_BY_ID_QUERY = "SELECT * FROM recipe WHERE id = ?";

    @Override
    public Recipe getById(long id) {
        Recipe recipe;
        try {
            recipe = jdbcTemplateObject.queryForObject(SELECT_BY_ID_QUERY,
                    new Object[]{id}, new RecipeMapper());
        } catch (Throwable e) {
            throw new ApplicationException(e.getMessage(), e);
        }
        return recipe;
    }


    private final String INSERT_QUERY = "INSERT INTO recipe (title) VALUES (?)";

    @Override
    public void insert(Recipe recipe) {
        validateRecipe(recipe);
        validateRecipeDuplicate(recipe);
        try {
            jdbcTemplateObject.update(INSERT_QUERY, recipe.getTitle());
        } catch (Throwable e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }


    private final String UPDATE_QUERY = "UPDATE recipe SET title = ? WHERE id = ?";

    @Override
    public void update(Recipe recipe) {
        validateRecipeId(recipe.getId());
        validateRecipe(recipe);
        try {
            jdbcTemplateObject.update(UPDATE_QUERY, recipe.getTitle(), recipe.getId());
        } catch (Throwable e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }

    private final String DELETE_BY_ID_QUERY = "DELETE FROM recipe where id = ?";

    @Override
    public void delete(long id) {
        validateRecipeId(id);
        try {
            jdbcTemplateObject.update(DELETE_BY_ID_QUERY, id);
        } catch (Throwable e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
