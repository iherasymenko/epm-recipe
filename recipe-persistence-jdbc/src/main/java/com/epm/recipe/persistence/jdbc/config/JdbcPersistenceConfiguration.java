package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.SQLException;

import javax.sql.DataSource;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public static DataBaseInitAfterContextRefreshed dbInit() {
        return new DataBaseInitAfterContextRefreshed();
    }

    @Bean
    public DataSource dataSource(@Value("${persistence.jdbc.driver}") final String driver,
                                 @Value("${persistence.jdbc.url}") final String url,
                                 @Value("${persistence.jdbc.user}") final String user,
                                 @Value("${persistence.jdbc.password}") final String password) throws SQLException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public RecipeRepository recipeRepository(JdbcOperations jdbcOperations, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        return new JdbcRecipeRepository(jdbcOperations, namedParameterJdbcOperations);
    }

}
