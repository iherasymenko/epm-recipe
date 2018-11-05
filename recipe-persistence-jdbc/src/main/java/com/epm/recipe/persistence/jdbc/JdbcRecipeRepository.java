package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.exceptions.RecipeRepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcRecipeRepository implements RecipeRepository {
    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private String FIND_ALL_RECIPES_QUERY = "select * from recipes";

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = jdbcTemplate.query(
                FIND_ALL_RECIPES_QUERY,
                (rs, rowNum) -> new Recipe(
                        rs.getString("title"),
                        rs.getLong("id")
                )
        );
        if (recipes.isEmpty()) {
            throw new RecipeRepositoryException("There is no recipes");
        }
        return recipes;
    }

    private String FIND_RECIPE_BY_ID_QUERY = "select * from recipes where id = ?";

    @Override
    public Recipe findById(long id) {
        List<Recipe> recipes = jdbcTemplate.query(
                FIND_RECIPE_BY_ID_QUERY,
                (rs, rowNum) -> new Recipe(
                        rs.getString("title"),
                        rs.getLong("id")
                ),
                id
        );
        if (recipes.isEmpty()) {
            throw new RecipeRepositoryException("There is no recipe with id = " + id);
        }
        return recipes.get(0);
    }

    private String CREATE_RECIPE_QUERY = "insert into recipes (title) values (?)";

    @Override
    public void create(Recipe recipe) {
        jdbcTemplate.update(
                CREATE_RECIPE_QUERY,
                recipe.title
        );
    }


    private String UPDATE_RECIPE_QUERY = "update recipes set title = ? where id = ?";

    @Override
    public void update(Recipe recipe) {
        checkIfRecipePresent(recipe.id);
        jdbcTemplate.update(
                UPDATE_RECIPE_QUERY,
                recipe.title,
                recipe.id
        );
    }

    private String DELETE_RECIPE_QUERY = "delete from recipes where id = ?";

    @Override
    public void delete(long id) {
        checkIfRecipePresent(id);
        jdbcTemplate.update(
                DELETE_RECIPE_QUERY,
                id
        );
    }

    private void checkIfRecipePresent(long id) {
        findById(id);
    }
}
