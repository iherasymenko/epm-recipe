package com.epm.recipe.persistence.in_db.dao;

import com.epm.recipe.persistence.in_db.dto.RecipeDto;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SpringJdbcDao implements RecipeDao {


    private JdbcTemplate jdbcTemplate;

    public SpringJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SQL_GET_ALL = "select * from RECIPE";

    public List<RecipeDto> findAll() {
        List<RecipeDto> recipeDtoList = jdbcTemplate.query(SQL_GET_ALL,
                (resultSet, i) -> {
                    RecipeDto recipeDto = new RecipeDto();
                    recipeDto.setId(resultSet.getLong("id"));
                    recipeDto.setTitle(resultSet.getString("Title"));
                    return recipeDto;
                });
        return recipeDtoList;
    }

    private final String SQL_ADD = "insert into RECIPE (TITLE) values (?)";

    @Override
    public void add(RecipeDto recipeDto) {
        Object[] inputs = new Object[]{recipeDto.getTitle()};
        jdbcTemplate.update(SQL_ADD, inputs);
    }

    private final String SQL_GET_BY_ID = "select * from RECIPE where id = ?";

    @Override
    public RecipeDto getById(long id) {
        RecipeDto recipeDto = jdbcTemplate.queryForObject(SQL_GET_BY_ID, (rs, rowNum) -> {
            final RecipeDto recipeDto1 = new RecipeDto();
            recipeDto1.setId(rs.getInt("id"));
            recipeDto1.setTitle(rs.getString("Title"));
            return recipeDto1;
        }, id);
        return recipeDto;
    }

    private final String SQL_REMOVE_BY_ID = "delete from RECIPE where id = ?";

    @Override
    public void remove(long id) {
        Object[] args = new Object[]{id};
        jdbcTemplate.update(SQL_REMOVE_BY_ID, args);
    }

    private final String SQL_UPDATE = "update RECIPE set title = ? where id = ?";

    @Override
    public void update(RecipeDto recipeDto) {
        jdbcTemplate.update(SQL_UPDATE, recipeDto.getTitle(), recipeDto.getId());

    }

    private final String SQL_DELETE_ALL = "delete from RECIPE";

    @Override
    public void deleteAll() {
        jdbcTemplate.update(SQL_DELETE_ALL);
    }
}
