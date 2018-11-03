package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(value= "/recipe/", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe>> getAllUsersWithRest() {
        List<Recipe> recipeList = recipeService.getAll();
        if (recipeList.isEmpty()) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);

    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> getByIdWithRest(@PathVariable("id") long id) {
        Recipe recipe =  recipeService.getById(id);
        if (recipe == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Recipe>(recipe, HttpStatus.OK);
    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Recipe> deleteByIdWithRest(@PathVariable("id") long id) {
        Recipe recipe =  recipeService.getById(id);
        if (recipe == null) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        recipeService.deleteById(id);
        return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/recipe/", method = RequestMethod.DELETE)
    public ResponseEntity<Recipe> deleteAllWithRest() {
        recipeService.deleteAll();
        return new ResponseEntity<Recipe>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateWithRest(@PathVariable long id, String title) {
        recipeService.add(new Recipe(title, id));
    }




}
