package com.epm.recipe.persistence.in_db;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DbRecipeRepository implements RecipeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("DROP TABLE recipes IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE recipes (\n" +
                "id INT(11) NOT NULL, title VARCHAR(50))");
        List<Object[]> initialData = List.of(
                new Object[]{10, "Pizza"},
                new Object[]{20, "Sandwich"}
        );
        jdbcTemplate.batchUpdate("INSERT INTO recipes(id, title) VALUES (?,?)", initialData);
    }

    @Override
    public List<Recipe> findAll() {
        return jdbcTemplate.query("SELECT * FROM recipes", (rs, rowNum) -> new Recipe(
                rs.getString("title"),
                rs.getInt("id")
        ));
    }

    @Override
    public boolean add(Recipe recipe) {
        jdbcTemplate.update("INSERT INTO recipes (id, title) values (?, ?)", recipe.getId(), recipe.getTitle());
        return getTitleBiId(recipe.getId()) != null;
    }

    @Override
    public Recipe removeById(int id) {
        Recipe recipe = new Recipe(getTitleBiId(id), id);
        jdbcTemplate.update("DELETE FROM recipes WHERE id = ?", id);
        return recipe;
    }

    @Override
    public Recipe updateValueById(int id, String value) {
        jdbcTemplate.update("UPDATE recipes SET title = ? WHERE id = ?", value, id);
        return new Recipe(getTitleBiId(id), id);
    }

    private String getTitleBiId(long id) {
        return jdbcTemplate.queryForObject("SELECT title FROM recipes WHERE id=" + id, String.class);
    }
}