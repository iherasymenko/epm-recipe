package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }


    @GetMapping("get_all_recipes")
    public ArrayList<Recipe> recipesOfTheDay() {
        return recipeService.getAllRecipes();
    }

    @PostMapping("add_new_recipe/{id}/{recipe}")
    public Recipe addNewRecipe(
            @PathVariable int id,
            @PathVariable String recipe) {
        return recipeService.addRecipe(id, recipe);
    }

    @PutMapping("update_recipe/{id}/{recipe}")
    public Recipe updateRecipe(
            @PathVariable int id,
            @PathVariable String recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("delete_recipe/{id}")
    public Recipe updateRecipe(@PathVariable int id) {
        return recipeService.deleteRecipe(id);
    }

}
