package com.epm.recipe.persistence.in_memory.mappers;

import com.epm.recipe.domain.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<Recipe> {

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Recipe(rs.getString("title"), rs.getLong("id"));
    }
}
