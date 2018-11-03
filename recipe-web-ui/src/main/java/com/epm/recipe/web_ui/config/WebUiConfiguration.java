package com.epm.recipe.web_ui.config;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.web_ui.controller.RecipeController;
import com.epm.recipe.web_ui.controller.RestRecipeController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@Import(ThymeleafConfiguration.class)
public class WebUiConfiguration extends WebMvcConfigurerAdapter
{

    @Bean
    RestRecipeController restRecipeController(RecipeService recipeService) {
        return new RestRecipeController(recipeService);
    }

    @Bean
    RecipeController recipeController(RecipeService recipeService) {
        return new RecipeController(recipeService);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
}
