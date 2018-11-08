package com.epm.recipe.persistence.in_memory;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class H2RecipeRepository  implements RecipeRepository {

    private static PropertiesManager propertiesManager = new PropertiesManager();

    private static Connection getConnection(){
        return DbManager.getConnection(propertiesManager.getApplicationProperties());
    }
    static {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "CREATE TABLE recipes (\n" +
                    "    id bigint auto_increment,\n" +
                    "    title varchar(255)\n" +
                    ");";
            stat.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Cant create db", e);
        }
    }

    @Override
    public List<Recipe> findAll() {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT recipes.id, recipes.title from recipes;";
            ResultSet resultSet = stat.executeQuery(sql);
            List<Recipe> apartmentClasses = new ArrayList<>();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                apartmentClasses.add(new Recipe(title, id));
            }
            return apartmentClasses;
        } catch (SQLException e) {
            throw new RuntimeException("Cant read entities", e);
        }
    }

    @Override
    public Optional<Recipe> read(long id) {
        try (Connection conn = getConnection()) {
            Statement stat = conn.createStatement();
            String sql = "SELECT recipes.id, recipes.title from recipes WHERE recipes.id=" + id + ";";
            ResultSet resultSet = stat.executeQuery(sql);
            if(resultSet.next()){
                long entityId = resultSet.getLong("id");
                String title = resultSet.getString("title");
                return Optional.of(new Recipe(title, entityId));
            }else {
                throw new RuntimeException("Cant read entity");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cant read entities", e);
        }
    }

    @Override
    public void createRecipe(String title) {
        String sql = "INSERT INTO recipes (title) VALUES (?)";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cant create entity", e);
        }
    }

    @Override
    public void updateRecipe(long id, String title) {
        String sql = "UPDATE recipes SET recipes.title=? WHERE recipes.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            statement.setLong(2, id);

            int rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cant update entity", e);
        }
    }

    @Override
    public void deleteRecipe(long id) {
        String sql = "DELETE FROM recipes WHERE recipes.id=?";
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cant delete entity", e);
        }
    }


}
