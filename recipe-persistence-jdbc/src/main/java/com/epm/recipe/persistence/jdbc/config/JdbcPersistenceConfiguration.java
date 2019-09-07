package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.UserRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;

import com.epm.recipe.persistence.jdbc.JdbcUserRepository;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.SQLException;

import javax.sql.DataSource;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public DataSource dataSource(@Value("${db.url}") String url,
                                 @Value("${db.user}") String user,
                                 @Value("${db.password}") String password) throws SQLException {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcOperations jdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public RecipeRepository recipeRepository(NamedParameterJdbcOperations jdbcOperations) {
        return new JdbcRecipeRepository(jdbcOperations);
    }

    @Bean
    public UserRepository userRepository(NamedParameterJdbcOperations jdbcOperations){
        return new JdbcUserRepository(jdbcOperations);
    }

}
