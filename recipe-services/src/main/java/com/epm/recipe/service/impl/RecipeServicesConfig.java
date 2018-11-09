package com.epm.recipe.service.impl;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecipeServicesConfig {

    @Bean
    public RecipeService recipeService(RecipeRepository recipeRepository) {
        return new DefaultRecipeService(recipeRepository);
    }
}
