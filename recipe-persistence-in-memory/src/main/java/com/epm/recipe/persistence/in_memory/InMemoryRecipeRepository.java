package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class InMemoryRecipeRepository implements RecipeRepository {
    private final String SQL_CREATE = "insert into recipe (title) values (?)";
    private final String SQL_FIND_ALL = "select * from recipe order by id";
    private final String SQL_UPDATE = "update recipe set title = ? where id = ?";
    private final String SQL_DELETE = "delete from recipe where id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryRecipeRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public boolean createRecipe(Recipe recipe) {
        return jdbcTemplate.update(SQL_CREATE, recipe.getTitle()) > 0;
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new RecipeMapper());
    }

    @Override
    public boolean updateRecipe(Recipe recipe) {
        return jdbcTemplate.update(SQL_UPDATE, recipe.getTitle(), recipe.getId()) > 0;
    }

    @Override
    public boolean deleteRecipe(long id) {
        return jdbcTemplate.update(SQL_DELETE, id) > 0;
    }
}
