package com.epm.recipe.service.impl.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.impl.DefaultRecipeService;

import com.epm.recipe.service.impl.RecipeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {

//    @Bean
//    public DefaultRecipeService recipeService(RecipeRepository recipeRepository) {
//        return new DefaultRecipeService(recipeRepository);
//    }
    @Bean
    public RecipeServiceImpl recipeService(RecipeRepository recipeRepository) {
        return new RecipeServiceImpl(recipeRepository);
    }

}
