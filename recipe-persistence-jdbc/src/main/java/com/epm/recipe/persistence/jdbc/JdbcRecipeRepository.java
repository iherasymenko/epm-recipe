package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcRecipeRepository implements RecipeRepository {

    private final static Logger log = LoggerFactory.getLogger(JdbcRecipeRepository.class);
    private final DataSource ds;

    public JdbcRecipeRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Recipe> findAll() {
        JdbcOperations jdbcTemplate = new JdbcTemplate(ds);

        return jdbcTemplate.query("SELECT id, title FROM recipes",
                (rs, rowNum) -> new Recipe(rs.getString("title"), rs.getLong("id")));
    }
}
