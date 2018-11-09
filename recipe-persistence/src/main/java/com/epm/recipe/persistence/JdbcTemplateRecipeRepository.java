package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class JdbcTemplateRecipeRepository implements RecipeRepository {

    private JdbcTemplate jdbcTemplate;

    private static Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Recipe(rs.getString("title"), rs.getLong("id"));
    }

    public JdbcTemplateRecipeRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Recipe> findAll() {

        return jdbcTemplate.query("SELECT recipes.id, recipes.title from recipes;", JdbcTemplateRecipeRepository::mapRow);

    }

    @Override
    public Optional<Recipe> read(long id) {
        return jdbcTemplate.query("SELECT recipes.id, recipes.title from recipes WHERE recipes.id=" + id + ";", JdbcTemplateRecipeRepository::mapRow)
                .stream()
                .findFirst();
    }

    @Override
    public void createRecipe(String title) {
        jdbcTemplate.update("INSERT INTO recipes (title) VALUES ('" + title + "')");
    }

    @Override
    public void updateRecipe(long id, String title) {
        jdbcTemplate.update("UPDATE recipes SET recipes.title='" + title + "' WHERE recipes.id=" + id + ";");
    }

    @Override
    public void deleteRecipe(long id) {
        jdbcTemplate.update("DELETE FROM recipes WHERE recipes.id=" + id + ";");
    }
}
