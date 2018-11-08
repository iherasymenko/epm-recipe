package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("recipe/{id}")
    public Recipe recipe(@PathVariable long id) {
        return recipeService.byId(id);
    }

    @GetMapping("recipe")
    public List<Recipe> recipe() {
        return recipeService.all();
    }

    @PostMapping("recipe/")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.add(recipe.getTitle());
    }

    @PutMapping("recipe/{id}")
    public ResponseEntity<Object> updateRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        if (recipe.getId() != id){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong recipe id!");
        }
        else {
            recipe = recipeService.update(recipe);
            return ResponseEntity.ok(recipe);
        }
    }

    @DeleteMapping("recipe/{id}")
    public boolean deleteRecipe(@PathVariable long id) {
        return recipeService.delete(id);
    }
}
