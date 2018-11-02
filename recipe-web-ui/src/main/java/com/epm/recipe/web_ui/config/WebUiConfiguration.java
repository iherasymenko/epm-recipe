package com.epm.recipe.web_ui.config;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.web_ui.Ui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebUiConfiguration {

    @Bean
    public Ui ui(RecipeService recipeService) {
        return new Ui(recipeService);
    }

}
