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

    @GetMapping("recipe/all")
    public List<Recipe> getRecipeList() {
        return recipeService.findAll();
    }

    @PutMapping("recipe/add")
    public Optional<Recipe> add(@RequestParam("id") int id, @RequestParam("title") String title) {
        if (recipeService.add(id, title))
            return recipeService.byId(id);
        return Optional.empty();
    }

    @DeleteMapping("recipe/delete/{id}")
    public Recipe removeById(@PathVariable int id) {
        return recipeService.removeById(id);
    }

    @PatchMapping("recipe/update")
    public Recipe updateById(@RequestParam("id") int id, @RequestParam("title") String title) {
        return recipeService.updateValueById(id, title);
    }
}
