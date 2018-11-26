package com.epm.recipe.persistence.in_mariadb;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbRecipeRepository implements RecipeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Recipe> findAll() {
        String SelectQuery = "SELECT * FROM recipes";
        return jdbcTemplate.query(SelectQuery, new RecipeRowMapper());
    }
    @Override
    public Recipe getById(long id) {
        String SelectByIdQuery = "SELECT * FROM recipes WHERE id = ?";
        return jdbcTemplate.queryForObject(SelectByIdQuery,
                new Object[]{id}, new RecipeRowMapper());
    }


    @Override
    public void insert(Recipe recipe) {
        String InsertQuery = "INSERT INTO recipes (title) VALUES (?)";
        jdbcTemplate.update(InsertQuery, recipe.getTitle());
    }
    @Override
    public void update(Recipe recipe) {
        String UpdateQuery = "UPDATE recipes SET title = ? WHERE id = ?";
        jdbcTemplate.update(UpdateQuery, recipe.getTitle(), recipe.getId());
    }
    @Override
    public void delete(long id) {
        String DeleteByIdQuery = "DELETE FROM recipes where id = ?";
        jdbcTemplate.update(DeleteByIdQuery, id);
    }
}

class RecipeRowMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getLong("id"));
        recipe.setTitle(rs.getString("title"));
        return recipe;
    }
}