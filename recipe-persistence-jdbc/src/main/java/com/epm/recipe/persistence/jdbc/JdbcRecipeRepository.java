package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.domain.Restaurant;
import com.epm.recipe.persistence.RecipeRepository;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JdbcRecipeRepository implements RecipeRepository {

    private final JdbcOperations jdbcOperations;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JdbcRecipeRepository(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbcOperations = Objects.requireNonNull(jdbcOperations, "jdbcOperations");
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcOperations.query("SELECT id, title, price FROM Recipes", (resultSet, __) -> new Recipe(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getInt("price")));
    }

    public Recipe getById(int id) {
        Map<String, Integer> namedParameters = Collections.singletonMap("id", id);
        final String GET_BY_ID = "SELECT id, title, price FROM Recipes WHERE id = :id";
        Recipe recipe = namedParameterJdbcOperations.queryForObject(GET_BY_ID, namedParameters,
                (resultSet, __) -> new Recipe(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getInt("price")));

        return recipe;
    }

    @Override
    public List<Recipe> getByRestaurant(Restaurant restaurant) {

      //  SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("restaurant", restaurant.name);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(restaurant);

        final String GET_BY_RESTAURANT = "SELECT id, title, price FROM Recipes WHERE restaurant = :name";

        List<Recipe> recipes = namedParameterJdbcOperations.query(GET_BY_RESTAURANT, namedParameters,
                (resultSet, __) -> new Recipe(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getInt("price")));

        return recipes;
    }


    @Override
    public void setup() {
          jdbcOperations.execute("CREATE TABLE Recipes (id INT(3) AUTO_INCREMENT PRIMARY KEY, title VARCHAR(20), restaurant VARCHAR(20), price INT(3))");
          jdbcOperations.execute("INSERT INTO Recipes (title, restaurant, price) VALUES ('soup', 'rose', 32)");
          jdbcOperations.execute("INSERT INTO Recipes (title, restaurant, price) VALUES ('pasta', 'may', 47)");
          jdbcOperations.execute("INSERT INTO Recipes (title, restaurant, price) VALUES ('pasta', 'wildroad', 147)");
    }

}
