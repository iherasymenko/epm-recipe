package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.DbRecipeRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DbPersistenceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${url}") String url,
                                 @Value("${user}") String user,
                                 @Value("${password}") String password) throws SQLException {
        MariaDbDataSource config = new MariaDbDataSource();
        config.setUrl(url);
        config.setUser(user);
        config.setPassword(password);
        return config;
    }

    @Bean
    public RecipeRepository recipeRepository(DataSource dataSource) {
        return new DbRecipeRepository(dataSource);
    }
}
