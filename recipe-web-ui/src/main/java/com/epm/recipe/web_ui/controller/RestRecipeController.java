package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
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
    public Recipe getRecipe(@PathVariable long id) throws SQLException {
        Recipe recipe = recipeService.byIdInDB(id);

        return recipe;
    }

    @GetMapping("recipe/all")
    public List<Recipe> gettAll() throws SQLException {
        return recipeService.allInDB();
    }

    @PostMapping("recipe/")
    public ResponseEntity<Recipe> postRecipe(@RequestBody Recipe recipe) {

        long id = recipeService.Create(recipe);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("recipe/{id}")
    public void putRecipe(@RequestBody Recipe recipe, @PathVariable long id) {

        recipeService.Update(recipe, id);
    }

    @DeleteMapping("recipe/{id}")
    public void deleteRecipe(@PathVariable long id) {
        recipeService.Delete(id);
    }
}