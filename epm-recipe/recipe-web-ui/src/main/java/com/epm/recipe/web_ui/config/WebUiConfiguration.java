package com.epm.recipe.web_ui.config;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.web_ui.controller.RecipeController;
import com.epm.recipe.web_ui.controller.RestRecipeController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(ThymeleafConfiguration.class)
public class WebUiConfiguration {

    @Bean
    RestRecipeController restRecipeController(RecipeService recipeService) {
        return new RestRecipeController(recipeService);
    }

    @Bean
    RecipeController recipeController(RecipeService recipeService) {
        return new RecipeController(recipeService);
    }

}
