package com.epm.recipe.persistence.database.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.database.DatabaseRecipeRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabasePersistenceConfiguration {

    @Bean
    public DataSource getDataSource(@Value("${db.url}") String url,
                                    @Value("${db.user}") String username,
                                    @Value("${db.password}") String password
    ) throws SQLException {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl(url);
        dataSource.setUserName(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RecipeRepository recipeRepository(JdbcOperations jdbcOperations) {
        return new DatabaseRecipeRepository(jdbcOperations);
    }
}
