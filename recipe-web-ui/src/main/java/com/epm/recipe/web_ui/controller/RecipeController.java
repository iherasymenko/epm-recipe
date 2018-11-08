package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(Api.ROOT)
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping(Api.RecipesUi.RECIPE_OF_THE_DAY_UI)
    public String recipeOfTheDay(Model model) {
        LOGGER.info("Getting recipe of the day...");
        model.addAttribute("title", recipeService.recipeOfTheDay().get().getTitle());
        model.addAttribute("id", recipeService.recipeOfTheDay().get().getId());
        return "recipe_of_the_day";
    }

    @GetMapping(Api.RecipesUi.ALL_RECIPES_UI)
    public String allRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAllRecipes());
        return "all_recipes";
    }

    @PostMapping(Api.RecipesUi.GET_RECIPE_UI)
    public String recipe(@ModelAttribute(name = "id") Long id, Model model) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);

        if (recipe.isPresent()) {
            model.addAttribute("title", recipe.get().getTitle());
            model.addAttribute("id", recipe.get().getId());
            return "recipe";
        }

        return Api.RecipesUi.REDIRECT;
    }

    @PostMapping(Api.RecipesUi.ADD_RECIPE_UI)
    public String addRecipe(@ModelAttribute(name = "recipe") Recipe recipe) {
        recipeService.insertRecipe(recipe);
        return Api.RecipesUi.REDIRECT;
    }

    @PostMapping(Api.RecipesUi.UPDATE_RECIPE_UI)
    public String updateRecipe(@ModelAttribute(name = "id") Long id,
                               @ModelAttribute(name = "recipe") Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return Api.RecipesUi.REDIRECT;
    }

    @PostMapping(Api.RecipesUi.DELETE_RECIPE_UI)
    public String deleteRecipe(@ModelAttribute(name = "id") Long id) {
        recipeService.deleteRecipe(id);
        return Api.RecipesUi.REDIRECT;
    }

    @GetMapping
    public String api() {
        return "api";
    }

    @GetMapping(Api.RecipesUi.ADD_RECIPE_UI)
    public String addRecipe() {
        return "add_recipe";
    }

    @GetMapping(Api.RecipesUi.GET_RECIPE_UI)
    public String getRecipe() {
        return "get_recipe";
    }

    @GetMapping(Api.RecipesUi.UPDATE_RECIPE_UI)
    public String updateRecipe() {
        return "update_recipe";
    }

    @GetMapping(Api.RecipesUi.DELETE_RECIPE_UI)
    public String deleteRecipe() {
        return "delete_recipe";
    }
}
