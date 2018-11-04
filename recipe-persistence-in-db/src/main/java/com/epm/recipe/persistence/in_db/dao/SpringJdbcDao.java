package com.epm.recipe.persistence.in_db.dao;

import com.epm.recipe.persistence.in_db.dto.RecipeDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SpringJdbcDao implements RecipeDao{

    private final String SQL_GET_ALL = "select * from RECIPE";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private DbConfig dbConfig;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public SpringJdbcDao() {
        dbConfig = new DbConfig();
        dataSource = dbConfig.dataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RecipeDto> findAll() {
        List<RecipeDto> recipeDtoList = jdbcTemplate.query(SQL_GET_ALL, new RecipeMapper());
        return recipeDtoList;
    }

    private final String SQL_ADD = "insert into RECIPE (TITLE) values (?)";
    @Override
    public void add(RecipeDto recipeDto) {
        Object[] inputs = new Object[] {recipeDto.getTitle()};
        jdbcTemplate.update(SQL_ADD, inputs);
    }

    private final String SQL_GET_BY_ID = "select * from RECIPE where id = ?";
    @Override
    public RecipeDto getById(long id) {
        int[] types = {Types.BIGINT};
        Object[] args = new Object[] {id};
//        RecipeDto recipeDto = jdbcTemplate.queryForObject(SQL_GET_BY_ID,
////                args,
////                new BeanPropertyRowMapper(RecipeDto.class));
//                RecipeDto.class,
//                id
//
//        );

        RecipeDto recipeDto =jdbcTemplate.queryForObject(SQL_GET_BY_ID, new RowMapper<RecipeDto>() {

            public RecipeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                final RecipeDto recipeDto1 = new RecipeDto();
                recipeDto1.setId(rs.getInt("id"));
                recipeDto1.setTitle(rs.getString("Title"));
                return recipeDto1;
            }
        }, id);
        return recipeDto;
    }

    private final String SQL_REMOVE_BY_ID = "delete from RECIPE where id = ?";
    @Override
    public void remove(long id) {
        Object[] args = new Object[] {id};
        jdbcTemplate.update(SQL_REMOVE_BY_ID, args);
    }

    private final String SQL_UPDATE = "update RECIPE set title = ? where id = ?";
    @Override
    public void update(RecipeDto recipeDto) {
        jdbcTemplate.update(SQL_UPDATE, recipeDto.getTitle(), recipeDto.getId());

    }
}
