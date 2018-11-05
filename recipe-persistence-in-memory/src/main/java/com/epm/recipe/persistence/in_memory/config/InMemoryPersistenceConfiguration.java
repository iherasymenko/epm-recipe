package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.in_memory.InMemoryRecipeRepository;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class InMemoryPersistenceConfiguration {

    @Bean
    public InMemoryRecipeRepository recipeRepository(JdbcTemplate jdbcTemplate) {
        return new InMemoryRecipeRepository(jdbcTemplate);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        DbConfig dbConfig = new DbConfig();
        dataSource.setDriverClassName(dbConfig.driver());
        dataSource.setUrl(dbConfig.url());
        dataSource.setUsername(dbConfig.user());
        dataSource.setPassword(dbConfig.password());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}