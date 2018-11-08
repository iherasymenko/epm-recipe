package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(Api.ROOT)
public class RestRecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestRecipeController.class);
    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping(Api.Recipes.RECIPE_OF_THE_DAY)
    public ResponseEntity<Recipe> recipeOfTheDay() {
        LOGGER.info("Getting recipe of the day...");

        Optional<Recipe> recipeOfTheDay = recipeService.recipeOfTheDay();

        return recipeOfTheDay.map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @GetMapping(Api.Recipes.RECIPE)
    public ResponseEntity<Recipe> recipe(@PathVariable(name = "id") Long id) {
        LOGGER.info(String.format("Getting the recipe by id: %d", id));

        Optional<Recipe> recipeById = recipeService.getRecipeById(id);

        return recipeById.map(recipe -> new ResponseEntity<>(recipe, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(Api.Recipes.RECIPES)
    public ResponseEntity<List<Recipe>> recipes() {
        LOGGER.info("Getting all available recipes in the system...");

        List<Recipe> recipes = recipeService.findAllRecipes();

        return recipes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(recipes, HttpStatus.OK);

    }

    @PostMapping(value = Api.Recipes.RECIPES)
    public ResponseEntity<Void> createRecipe(@RequestBody Recipe recipe, UriComponentsBuilder uriCompBuilder) {
        recipeService.insertRecipe(recipe);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCompBuilder.path("/recipes/").buildAndExpand(recipe.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(Api.Recipes.RECIPE)
    public ResponseEntity<Void> updateRecipe(@PathVariable(name = "id") Long id, @RequestBody Recipe recipe) {
        LOGGER.info(String.format("Updating the recipe by id: %d", id));

        Optional<Recipe> updatedRecipe = recipeService.getRecipeById(id);

        if (updatedRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipeService.updateRecipe(id, recipe);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(Api.Recipes.RECIPE)
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id) {
        LOGGER.info(String.format("Trying to delete recipe by id: %d", id));

        Optional<Recipe> removedRecipe = recipeService.getRecipeById(id);

        if (removedRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        recipeService.deleteRecipe(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
