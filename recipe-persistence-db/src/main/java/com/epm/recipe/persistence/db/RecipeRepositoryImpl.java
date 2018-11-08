package com.epm.recipe.persistence.db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class RecipeRepositoryImpl implements RecipeRepository, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeRepositoryImpl.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RecipeRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get one by id
    private static final String GET_RECIPE_QUERY = "SELECT * FROM recipes WHERE id = :id";
    //Find all
    private static final String GET_RECIPES_QUERY = "SELECT * FROM recipes ORDER BY id";
    //Create
    private static final String INSERT_RECIPE_QUERY = "INSERT INTO recipes (title) VALUES (:title)";
    //Save
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipes SET title = :title WHERE id = :id";
    //Delete
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipes WHERE id = :id";

    @Override
    public void afterPropertiesSet() {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("NamedParameterJdbcTemplate must be set!");
        }
    }

    @Override
    public List<Recipe> findAllRecipes() {
        LOGGER.info("Getting all available recipes in the system...");
        return jdbcTemplate.query(
                GET_RECIPES_QUERY, new RecipeRowMapper());
    }

    @Override
    public Optional<Recipe> getRecipeById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        LOGGER.info(String.format("Getting the recipe by id: %d", id));

        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(
                    GET_RECIPE_QUERY, parameters, new RecipeRowMapper())));
        } catch (EmptyResultDataAccessException exc) {
            return Optional.empty();
        }
    }

    @Override
    public void insertRecipe(Recipe recipe) {
        LOGGER.info(String.format("Trying to add the new recipe: %s", recipe.getTitle()));

        Recipe newRecipe = new Recipe(recipe.getTitle(), recipe.getId());
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(newRecipe);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_RECIPE_QUERY, parameters, keyHolder);
    }

    @Override
    public void updateRecipe(Long id, Recipe recipe) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("title", new SqlParameterValue(Types.VARCHAR, recipe.getTitle()));
        parameters.addValue("id", new SqlParameterValue(Types.INTEGER, id));

        LOGGER.info(String.format("Trying to update the recipe %s with id: %d", recipe.getTitle(), id));

        jdbcTemplate.update(UPDATE_RECIPE_QUERY, parameters);
    }

    @Override
    public void deleteRecipe(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        LOGGER.info(String.format("Removing the recipe with id: %d", id));

        jdbcTemplate.update(DELETE_RECIPE_QUERY, parameters);
    }

    private static final class RecipeRowMapper implements RowMapper<Recipe> {

        @Override
        public Recipe mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            return new Recipe(
                    resultSet.getString("title"),
                    resultSet.getLong("id")
            );
        }
    }
}
