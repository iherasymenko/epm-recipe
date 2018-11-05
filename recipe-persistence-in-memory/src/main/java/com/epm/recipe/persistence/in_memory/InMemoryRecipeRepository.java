package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class InMemoryRecipeRepository implements RecipeRepository {

    private JdbcTemplate jdbcTemplate;

    public InMemoryRecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTableRecipes();
        initializeTableRecipes();
    }

    @Override
    public List<Recipe> findAll() {
        String sqlQuery = "SELECT * FROM recipes";
        return jdbcTemplate.queryForObject(sqlQuery,
                (resultSet, rowNumber) -> {
                    ArrayList<Recipe> recipes = new ArrayList<>();
                    do {
                        Recipe recipe = new Recipe(
                                resultSet.getLong(1),
                                resultSet.getString(2)
                        );
                        recipes.add(recipe);
                    } while (resultSet.next());
                    return recipes;
                });
    }

    @Override
    public Recipe findOneById(Long id) {
        String sqlQuery = "SELECT * FROM recipes WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery,
                (resultSet, rowNumber) -> new Recipe(
                        resultSet.getLong(1),
                        resultSet.getString(2)),
                id);
    }

    @Override
    public void create(Recipe recipe) {
        String sqlQuery = "INSERT INTO recipes(title) VALUES (?)";
        jdbcTemplate.update(sqlQuery, recipe.getTitle());
    }

    @Override
    public void update(Recipe recipe) {
        String sqlQuery = "UPDATE recipes SET title = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, recipe.getTitle(), recipe.getId());
    }

    @Override
    public void delete(Recipe recipe) {
        String sqlQuery = "DELETE FROM recipes WHERE id = ?";
        jdbcTemplate.update(sqlQuery, recipe.getId());
    }


    private void createTableRecipes() {
        jdbcTemplate.execute(
                "DROP TABLE IF EXISTS recipes;"
                        + "CREATE TABLE IF NOT EXISTS recipes ("
                        + "  id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "  title VARCHAR(255) NOT NULL"
                        + ");"
        );
    }

    private void initializeTableRecipes() {
        jdbcTemplate.execute(
                "INSERT INTO `recipes` (`title`) VALUES ('Cake');"
                        + "INSERT INTO `recipes` (`title`) VALUES ('Sandwiches');"
                        + "INSERT INTO `recipes` (`title`) VALUES ('Pizza');"
                        + "INSERT INTO `recipes` (`title`) VALUES ('Burgers');"
                        + "INSERT INTO `recipes` (`title`) VALUES ('Cannelloni');"
        );
    }
}
