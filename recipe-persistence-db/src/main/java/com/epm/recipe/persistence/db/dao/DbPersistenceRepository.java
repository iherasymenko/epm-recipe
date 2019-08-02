package com.epm.recipe.persistence.db.dao;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.db.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbPersistenceRepository extends DbConnection implements RecipeRepository {

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();

        int id;
        String title;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(RecipeQueries.GET_ALL.QUERY);
             ResultSet rs = preparedStatement.executeQuery()) {

            while(rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");

                recipes.add(new Recipe(title, id));
            }
        } catch(SQLException ex) {
        //    logger.error("{}", ex);
            ex.printStackTrace();
        }
        //logger.info("Get all orders: {}", orders);
        return recipes;
    }

    private enum RecipeQueries {
        GET_ALL("SELECT * FROM Recipes");
        String QUERY;
        RecipeQueries(String QUERY) { this.QUERY = QUERY; }
    }
}
