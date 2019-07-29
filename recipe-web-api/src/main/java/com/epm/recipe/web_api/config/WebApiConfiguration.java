package com.epm.recipe.web_api.config;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.web_api.controller.RestRecipeController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class WebApiConfiguration {

    @Bean
    RestRecipeController restRecipeController(RecipeService recipeService) {
        return new RestRecipeController(recipeService);
    }

}
