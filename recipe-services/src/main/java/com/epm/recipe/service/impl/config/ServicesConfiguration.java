package com.epm.recipe.service.impl.config;

import com.epm.recipe.converter.RecipeConverter;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.impl.DefaultRecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

    @Bean
    public DefaultRecipeService recipeService(RecipeRepository recipeRepository, RecipeConverter recipeConverter) {
        return new DefaultRecipeService(recipeRepository, recipeConverter);
    }

}
