package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(Api.ROOT)
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping(Api.Recipes.RECIPE_OF_THE_DAY)
    public Recipe recipeOfTheDay() {
        return recipeService.recipeOfTheDay();
    }

    @GetMapping(Api.Recipes.RECIPE)
    public Optional<Recipe> recipe(@PathVariable(value = "recipe_id") long id) {
        return recipeService.byId(id);
    }

}
