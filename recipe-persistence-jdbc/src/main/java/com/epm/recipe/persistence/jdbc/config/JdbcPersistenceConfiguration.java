package com.epm.recipe.persistence.jdbc.config;


import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class JdbcPersistenceConfiguration {

    public DataSource dataSource() throws SQLException {
        MariaDbDataSource ds = new MariaDbDataSource();
        ds.setUrl("jdbc:mariadb://91.203.62.149:3306/epam?serverTimezone=UTC");
        ds.setUser("root");
        ds.setPassword("$Epam2019");

        return ds;
    }
    @Bean
    public RecipeRepository recipeRepository() throws SQLException {
        return new JdbcRecipeRepository(dataSource());
    }
}
