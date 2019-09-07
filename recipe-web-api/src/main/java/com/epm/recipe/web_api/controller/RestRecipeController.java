package com.epm.recipe.web_api.controller;

import com.epm.recipe.domain.recipe.Recipe;
import com.epm.recipe.domain.user.UserIdentity;
import com.epm.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("recipe_of_the_day")
    public Recipe recipeOfTheDay(UserIdentity ignored) {
        return recipeService.recipeOfTheDay();
    }

    @GetMapping("recipe/{id}")
    public Optional<Recipe> recipe(@PathVariable long id, UserIdentity ignored) {
        return recipeService.byId(id);
    }

}
