package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/recipe")
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("recipe_of_the_day")
    public Recipe recipeOfTheDay() {
        return recipeService.recipeOfTheDay();
    }

    @PostMapping("/")
    public void createRecipe(@RequestParam(value="title") String title) {
        recipeService.createRecipe(title);
    }

    @GetMapping("/{id}")
    public Optional<Recipe> readRecipe(@PathVariable long id) {
        return recipeService.readRecipe(id);
    }

    @PutMapping("/{id}")
    public void updateRecipe(@PathVariable long id, @RequestParam(value="title") String title) {
        recipeService.updateRecipe(id, title);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
    }

    @GetMapping("/")
    public List<Recipe> readAllRecipes() {
        return recipeService.readAll();
    }
}
