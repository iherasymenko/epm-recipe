package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcPersistenceRecipeRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository(DataSource dataSource){
        return new JdbcPersistenceRecipeRepository(dataSource);
    }

    @Bean
    public DataSource dataSource(@Value("${url}") String url, @Value("${user}") String user, @Value("${password}") String password){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPassword(password);
        hikariConfig.setUsername(user);
        hikariConfig.setJdbcUrl(url);
        return new HikariDataSource(hikariConfig);
    }

}
