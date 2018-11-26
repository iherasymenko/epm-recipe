package com.epm.recipe.web_ui.controller;

import org.springframework.web.bind.annotation.*;
import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("restapi")
public class RestRecipeController {
    private final RecipeService recipeService;
    public RestRecipeController(RecipeService recService) {
        this.recipeService = Objects.requireNonNull(recService, "recipeService");
    }
    @GetMapping("RecipeOfTheDay")
    public Recipe recipeOfTheDay() {
        return recipeService.recipeOfTheDay();
    }
    @GetMapping("recipe/{id}")
    public Recipe recipe(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    @GetMapping("recipes")
    public List<Recipe> findAllRecipes() {
        return recipeService.findAll();
    }

    @PostMapping("recipe")
    public void createRecipe(@RequestBody Recipe recipe) {
        recipeService.createRecipe(recipe);
    }

    @PutMapping("recipe/{id}")
    public void updateRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        recipe.setId(id);
        recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("recipe/{id}")
    public void deleteRecipe(@PathVariable long id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipeService.deleteRecipe(recipe);
    }

}
