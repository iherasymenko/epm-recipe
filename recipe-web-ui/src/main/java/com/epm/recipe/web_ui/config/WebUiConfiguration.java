package com.epm.recipe.web_ui.config;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.web_ui.controller.RecipeController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@Import(ThymeleafConfiguration.class)
public class WebUiConfiguration {

    @Bean
    RecipeController recipeController(RecipeService recipeService) {
        return new RecipeController(recipeService);
    }

}
