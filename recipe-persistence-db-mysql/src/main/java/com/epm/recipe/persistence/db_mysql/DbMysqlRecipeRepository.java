package com.epm.recipe.persistence.db_mysql;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DbMysqlRecipeRepository implements RecipeRepository {
    private JdbcTemplate jdbcTemplate;

    public DbMysqlRecipeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        String sql = "SELECT * FROM recipes";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String title = rs.getString("title");
            return new Recipe(title, id);
        });
    }
}
