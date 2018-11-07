package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_memory.mappers.RecipeMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class InMemoryRecipeRepository implements RecipeRepository {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Recipe> findAll() {
        String SQL = "SELECT * FROM recipes";
        return jdbcTemplate.query(SQL, new RecipeMapper());
    }

    @Override
    public Recipe findById(long id) {
        String SQL = "SELECT * FROM recipes WHERE id = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new RecipeMapper());
    }

    @Override
    public boolean create(Recipe recipe) {
        String SQL = "INSERT INTO recipes (title) VALUES (?)";
        int res = jdbcTemplate.update(SQL, recipe.getTitle());
        return res==1;
    }

    @Override
    public boolean update(Recipe recipe) {
        String SQL = "UPDATE recipes SET title = ? WHERE id = ?";
        int res = jdbcTemplate.update(SQL, recipe.getTitle(), recipe.getId());
        return res == 1;
    }

    @Override
    public boolean delete(long id) {
        String SQL = "DELETE FROM recipes WHERE id = ?";
        int res = jdbcTemplate.update(SQL, id);
        return res == 1;
    }
}
