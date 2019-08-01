package com.epm.recipe.persistence.jdbc.config;


import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import com.epm.recipe.properties.PropertiesManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new JdbcRecipeRepository(new DBConfiguration(new PropertiesManager()));
    }
}
