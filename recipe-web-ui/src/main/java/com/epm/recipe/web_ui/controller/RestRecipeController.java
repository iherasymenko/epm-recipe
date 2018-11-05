package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api")
public class RestRecipeController {

    private final RecipeService recipeService;

    public RestRecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @RequestMapping(value= "/recipes/", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe>> getAllUsersWithRest() {
        List<Recipe> recipeList = recipeService.getAll();
        if (recipeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recipeList, HttpStatus.OK);

    }

    @RequestMapping(value= "/recipes/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> getByIdWithRest(@PathVariable("id") long id) {
        Recipe recipe =  recipeService.getById(id);
        if (recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @RequestMapping(value= "/recipes/{id}/", method = RequestMethod.DELETE)
    public ResponseEntity<Recipe> deleteByIdWithRest(@PathVariable("id") long id) {
        Recipe recipe =  recipeService.getById(id);
        if (recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/recipes/", method = RequestMethod.DELETE)
    public ResponseEntity<Recipe> deleteAllWithRest() {
        recipeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/recipes/", method = RequestMethod.POST)
    public ResponseEntity<Void> createWithRest(@RequestBody Recipe recipe, UriComponentsBuilder uriComponentsBuilder) {
        recipeService.add(recipe);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/recipe/{id}").buildAndExpand(recipe.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value= "/recipes/{id}/", method = RequestMethod.PUT)
    public ResponseEntity<Recipe> updateWithRest(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        Recipe currentRecipe = recipeService.getById(id);
        if (currentRecipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentRecipe.setTitle(recipe.getTitle());
        recipeService.update(currentRecipe);
        return new ResponseEntity<>(currentRecipe, HttpStatus.OK);
    }


}
