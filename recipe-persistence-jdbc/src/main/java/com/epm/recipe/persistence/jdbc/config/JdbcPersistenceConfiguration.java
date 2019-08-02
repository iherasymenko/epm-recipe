package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcPersistenceRecipeRepository;
import com.epm.recipe.persistence.jdbc.connectionPool.ConnectionProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPersistenceConfiguration {
    @Bean
    public RecipeRepository recipeRepository(ConnectionProducer connectionProducer){
        return new JdbcPersistenceRecipeRepository(connectionProducer); }
}
