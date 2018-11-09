package com.epm.recipe.persistence.db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcRecipeRepository implements RecipeRepository {

    private static final String UPDATE_RECIPE = "update recipe SET title=:title where id=:id";
    private static final String DELETE_RECIPE = "delete from recipe where id=:id";
    private static final String SELECT_FROM_RECIPE = "select * from recipe";
    private static final String INSERT_RECIPE = "insert into recipe (title) values (:title)";
    private static final String SELECT_BY_ID = "select * from recipe where id=:id";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {

        List<Recipe>  recipeList =  namedParameterJdbcTemplate.query(SELECT_FROM_RECIPE, (rs, rowNum) ->
                new Recipe(rs.getString("title"), rs.getLong("id")));
        if(recipeList.isEmpty())
            return new ArrayList<Recipe>();
        return recipeList;
    }


    @Override
    public void save(Recipe recipe) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("title", recipe.title);
        namedParameterJdbcTemplate.update(INSERT_RECIPE, namedParameters);
    }

    @Override
    public void update(Recipe recipe) {
        if (getById(recipe.id) != null) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", recipe.id);
            namedParameters.addValue("title", recipe.title);
            namedParameterJdbcTemplate.update(UPDATE_RECIPE, namedParameters);
        } else {
            throw new MyIoCException("Error update: There isn't Recipe with id= " + recipe.id);
        }
    }

    @Override
    public void delete(Long id) {
        if (getById(id) != null) {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", id);
            namedParameterJdbcTemplate.update(DELETE_RECIPE, namedParameters);
        } else {
            throw new MyIoCException("Error update: There isn't Recipe with id= " + id);
        }
    }

    @Override
    public Recipe getById(Long recipeId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", recipeId);
        return Objects.requireNonNull(
                namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, (resultSet, row) ->
                        new Recipe(resultSet.getString("title"), resultSet.getLong("id"))
                ));
    }

}
