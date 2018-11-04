package com.epm.recipe.web_ui.controller;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;
import com.epm.recipe.service.exceptions.NoRecipeWithSuchIdException;
import com.epm.recipe.service.exceptions.NoRecipesException;
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

    @GetMapping("recipe_of_the_day")
    public ResponseEntity<ViewRecipeDto> recipeOfTheDay() {
        try {
            return new ResponseEntity<>(recipeService.recipeOfTheDay(), HttpStatus.OK);
        } catch (NoRecipesException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ViewRecipeDto>> getAllRecipes() {
        try {
            return new ResponseEntity<>(recipeService.getAll(), HttpStatus.OK);
        } catch (NoRecipesException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ViewRecipeDto> getRecipe(@PathVariable long id) {
        try {
            return new ResponseEntity<>(recipeService.getById(id), HttpStatus.OK);
        } catch (NoRecipeWithSuchIdException c) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createRecipe(@RequestBody CreateRecipeDto recipeDto) {
        recipeService.create(recipeDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ViewRecipeDto> updateRecipe(@RequestBody UpdateRecipeDto recipeDto) {
        try {
            return new ResponseEntity<>(recipeService.update(recipeDto), HttpStatus.OK);
        } catch (NoRecipeWithSuchIdException c) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRecipe(@PathVariable long id) {
        try {
            recipeService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (NoRecipeWithSuchIdException c) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
