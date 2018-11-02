package com.epm.recipe.persistence.in_memory.config;

import com.epm.recipe.persistence.in_memory.InMemoryRecipeRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryPersistenceConfiguration {

    @Bean
    public InMemoryRecipeRepository recipeRepository() {
        return new InMemoryRecipeRepository();
    }

}
