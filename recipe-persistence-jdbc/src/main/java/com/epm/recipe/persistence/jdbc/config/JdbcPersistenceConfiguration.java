package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

import javax.sql.DataSource;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${persistence.jdbc.url}") String url,
                                 @Value("${persistence.jdbc.user}") String user,
                                 @Value("${persistence.jdbc.password}") String password) throws SQLException {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public RecipeRepository recipeRepository(JdbcOperations jdbcOperations) {
        return new JdbcRecipeRepository(jdbcOperations);
    }

}
