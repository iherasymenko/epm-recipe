package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.http.MediaType;
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
        return recipeService.findById(id);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Recipe> listData() {
        return recipeService.findAll();
    }

    @RequestMapping(value="/create",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE },
            method=RequestMethod.POST)
    @ResponseBody
    public Recipe save(@RequestBody Recipe recipe) {
        recipeService.create(recipe);
        return recipe;
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Recipe recipe,
                      @PathVariable Long id) {
        recipeService.update(recipe);
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        Recipe recipe = Objects.requireNonNull(recipeService.findById(id).get());
        recipeService.delete(id);
    }

}
