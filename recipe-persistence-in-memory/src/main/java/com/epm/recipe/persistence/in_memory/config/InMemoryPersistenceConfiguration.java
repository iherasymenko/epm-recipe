package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_memory.H2RecipeRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryPersistenceConfiguration {

    @Bean
    public RecipeRepository recipeRepository() {
        return new H2RecipeRepository();
    }

}
