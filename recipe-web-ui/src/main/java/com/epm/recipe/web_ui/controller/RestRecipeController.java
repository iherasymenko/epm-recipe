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

    @GetMapping("recipes")
    public List<Recipe> recipes() {
        return recipeService.findAll();
    }

    @PostMapping("newRecipe")
    public boolean create(@RequestBody Recipe recipe) {
        return recipeService.create(recipe);
    }

    @PutMapping("update")
    public boolean update(@RequestBody Recipe recipe) {
        return recipeService.update(recipe);
    }

    @DeleteMapping("delete/{id}")
    public boolean delete(@PathVariable long id) {
        return recipeService.delete(id);
    }

}
