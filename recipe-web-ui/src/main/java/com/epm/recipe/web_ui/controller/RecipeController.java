package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("/")
    public String recipeOfTheDay(Model model) {
        model.addAttribute("recipe", recipeService.recipeOfTheDay());
        return "recipe";
    }

    @GetMapping("/create")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe("", -1));
        return "create_recipe";
    }

    @PostMapping("/create")
    public RedirectView createRecipe(@ModelAttribute Recipe recipe) {
        recipeService.createRecipe(recipe);
        return new RedirectView("/list");
    }

    @GetMapping("/list")
    public String recipesList(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes_list";
    }

    @GetMapping("/update/{id}")
    public String updateRecipeForm(Model model, @PathVariable String id) {
        model.addAttribute("recipe", recipeService.byId(Long.parseLong(id)).get());
        return "update_recipe";
    }

    @PostMapping("/update")
    public RedirectView updateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return new RedirectView("/list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteRecipe(@PathVariable String id) {
        recipeService.deleteRecipe(Long.parseLong(id));
        return new RedirectView("/list");
    }
}
