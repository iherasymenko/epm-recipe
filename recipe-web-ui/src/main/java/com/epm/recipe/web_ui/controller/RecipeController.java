package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("/recipe_of_the_day")
    public String recipeOfTheDay(Model model) {
        model.addAttribute("recipe", recipeService.recipeOfTheDay());
        return "recipe";
    }

    @GetMapping("/recipe/{id}")
    public String recipe(Model model, @PathVariable long id) {
        System.out.println(recipeService.byId(id));
        model.addAttribute("recipe", recipeService.byId(id));
        return "recipe";
    }

    @GetMapping("/recipe")
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeService.all());
        return "recipes";
    }

    @GetMapping("/add_recipe")
    public String addRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add_recipe";
    }

    @PostMapping("/add_recipe")
    public String addRecipe(@ModelAttribute Recipe recipe, Model model) {
        model.addAttribute("recipe", recipeService.add(recipe.getTitle()));
        return "added_recipe";
    }

    @GetMapping("/update_recipe")
    public String updateRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "update_recipe";
    }

    @PostMapping("/update_recipe")
    public String updateRecipe(@ModelAttribute Recipe recipe, Model model) {
        model.addAttribute("recipe", recipeService.update(recipe));
        return "updated_recipe";
    }

    @GetMapping("/delete_recipe")
    public String deleteRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "delete_recipe";
    }

    @PostMapping("/delete_recipe")
    public String deleteRecipe(@ModelAttribute Recipe recipe, Model model) {
        recipe = recipeService.byId(recipe.getId());
        recipeService.delete(recipe.getId());
        model.addAttribute("recipe", recipe);
        return "deleted_recipe";
    }
}
