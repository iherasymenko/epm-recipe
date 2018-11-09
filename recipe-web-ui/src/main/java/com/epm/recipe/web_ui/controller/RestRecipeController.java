package com.epm.recipe.web_ui.controller;

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
    public Optional<Recipe> recipe(@PathVariable long id) {
        return recipeService.byId(id);
    }

    @PostMapping("recipe")
    public boolean create(@RequestBody Recipe recipe) {
        return recipeService.createRecipe(recipe);
    }

    @GetMapping("recipes")
    public List<Recipe> recipesList() {
        return recipeService.findAll();
    }

    @PutMapping("recipe")
    public boolean update(@RequestBody Recipe recipe) {
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("recipe/{id}")
    public boolean delete(@PathVariable long id) {
        return recipeService.deleteRecipe(id);
    }
}
