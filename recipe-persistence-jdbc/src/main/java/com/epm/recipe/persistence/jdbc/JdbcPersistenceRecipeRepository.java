package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.exception.JdbcPersistenceException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcPersistenceRecipeRepository implements RecipeRepository {

    private final DataSource dataSource;

    public JdbcPersistenceRecipeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Recipe getRecipeFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String title = resultSet.getString(2);
        return new Recipe(title, id);
    }

    @Override
    public List<Recipe> findAll() {
        final String query = "SELECT * FROM recipes";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)){
            List<Recipe> recipes = new ArrayList<>();
            while (resultSet.next()){
                Recipe recipe = getRecipeFromResultSet(resultSet);
                recipes.add(recipe);
            }
            return recipes;
        } catch (SQLException ex){
            throw new JdbcPersistenceException(ex.getMessage());
        }
    }

}
