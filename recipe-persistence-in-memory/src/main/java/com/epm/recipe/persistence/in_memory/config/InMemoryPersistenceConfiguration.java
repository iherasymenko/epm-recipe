package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.in_memory.InMemoryRecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ResourceBundle;

@Configuration
public class InMemoryPersistenceConfiguration {

    @Bean
    public InMemoryRecipeRepository recipeRepository() {
        ResourceBundle properties = ResourceBundle.getBundle(("liquibase"));
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(properties.getString("url"));
        driverManagerDataSource.setUsername(properties.getString("username"));
        driverManagerDataSource.setPassword(properties.getString("password"));
        driverManagerDataSource.setDriverClassName(properties.getString("driver"));
        return new InMemoryRecipeRepository(driverManagerDataSource);
    }
}
