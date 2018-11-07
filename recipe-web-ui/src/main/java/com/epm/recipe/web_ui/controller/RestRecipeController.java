package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("recipe")
    public List<Recipe> findAllRecipes() {
        return recipeService.findAll();
    }

    @GetMapping("recipe/{id}")
    public Recipe recipe(@PathVariable long id) {
        return recipeService.findById(id);
    }

    @PostMapping("recipe")
    public void createRecipe(@RequestBody Recipe recipe) {
        recipeService.create(recipe);
    }

    @PutMapping("recipe/{id}")
    public void updateRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipe.setId(id);
        recipeService.update(recipe);
    }

    @DeleteMapping("recipe/{id}")
    public void deleteRecipe(@PathVariable long id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipeService.delete(recipe);
    }

}
