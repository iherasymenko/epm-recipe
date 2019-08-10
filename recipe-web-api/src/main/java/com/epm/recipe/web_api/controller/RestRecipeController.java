package com.epm.recipe.web_api.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Recipe recipeOfTheDay() {
        return recipeService.recipeOfTheDay();
    }

    @GetMapping("recipe/{id}")
    public Recipe recipe(@PathVariable int id) {
        return recipeService.byId(id);
    }

    @GetMapping("restaurant/{restaurant}")
    public List<Recipe> insert(@PathVariable String restaurant) {
        return recipeService.getByRestaurant(restaurant);
    }

}
