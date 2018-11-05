package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import(HikariDataSourceConfiguration.class)
public class JdbcPersistenceConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcRecipeRepository recipeRepository(JdbcTemplate jdbcTemplate){
        return new JdbcRecipeRepository(jdbcTemplate);
    }

}
