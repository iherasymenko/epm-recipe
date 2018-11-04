package com.epm.recipe.persistence.in_db.dao;

import com.epm.recipe.persistence.in_db.dto.RecipeDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper<RecipeDto> {
    public RecipeDto mapRow(ResultSet resultSet, int i) throws SQLException {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(resultSet.getLong("id"));
        recipeDto.setTitle(resultSet.getString("Title"));
        return recipeDto;
    }
}
