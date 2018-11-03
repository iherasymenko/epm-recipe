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

    @RequestMapping(value= "/recipe/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Recipe>> getAllUsersWithRest() {

        List<Recipe> recipeList = recipeService.getAll();
        if (recipeList.isEmpty()) {
            return new ResponseEntity<List<Recipe>>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<List<Recipe>>(recipeList, HttpStatus.OK);
        }
    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Recipe getByIdWithRest(@PathVariable long id) {
        return recipeService.getById(id);
    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteByIdWithRest(@PathVariable long id) {
        recipeService.deleteById(id);
    }

    @RequestMapping(value= "/recipe/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateWithRest(@PathVariable long id, String title) {
        recipeService.add(new Recipe(title, id));
    }




}
