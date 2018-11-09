package com.epm.recipe.web_ui.controller;


import com.epm.recipe.service.RecipeService;

import com.epm.recipe.persistence.in_memory.exception.RecipeNotFoundException;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/recipes")
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("")
    public ResponseEntity<List<ShowRecipe>> getAllRecipes() {
        try {
            return new ResponseEntity<>(recipeService.getAll(), HttpStatus.OK);
        } catch (RecipeNotFoundException e) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }

    @GetMapping("{id}")
    public ResponseEntity<ShowRecipe> getRecipe(@PathVariable long id) {
        try {
            return new ResponseEntity<>(recipeService.getById(id), HttpStatus.OK);
        } catch (RecipeNotFoundException c) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }

    @GetMapping("recipe_of_the_day")
    public ResponseEntity<ShowRecipe> recipeOfTheDay() {
        try {
            return new ResponseEntity<>(recipeService.recipeOfTheDay(), HttpStatus.OK);
        } catch (RecipeNotFoundException e) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }

    @PostMapping("")
    public ResponseEntity createRecipe(@RequestBody CreateRecipe createRecipe) {
        recipeService.create(createRecipe);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRecipe(@PathVariable long id) {
        try {
            recipeService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RecipeNotFoundException c) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }

    @PutMapping("")
    public ResponseEntity<ShowRecipe> updateRecipe(@RequestBody UpdateRecipe recipeUpdate) {
        try {
            return new ResponseEntity<>(recipeService.update(recipeUpdate), HttpStatus.OK);
        } catch (RecipeNotFoundException c) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
    }
}
