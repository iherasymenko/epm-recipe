package com.epm.recipe.persistence.db_mysql;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.db_mysql.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbMysqlRecipeRepository implements RecipeRepository {
//    private static final Logger LOG = LoggerFactory.getLogger(DbMysqlRecipeRepository.class);
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes";
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipe = extractFromResultSet(resultSet);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
//            System.out.println(e);
            throw new PersistenceException("Can't process getting of all users", e);
        }
        return recipes;
    }

    private Recipe extractFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        return new Recipe(title, id);
    }
}
