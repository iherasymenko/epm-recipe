package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<Recipe> {
    public Recipe mapRow(ResultSet rs, int i) throws SQLException {
        return new Recipe(rs.getString("title"), rs.getInt("id"));
    }
}
