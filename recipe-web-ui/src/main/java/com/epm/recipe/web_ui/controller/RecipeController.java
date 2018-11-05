package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @RequestMapping(value = { "/", "/recipe" }, method = RequestMethod.GET)
    public String recipeOfTheDay(Model model) {
        return "redirect:/recipe/list";
    }

    @RequestMapping("recipe/list")
    public String showAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAll());
        return "list";
    }

    @RequestMapping(value = "recipe/add", method = RequestMethod.GET)
    public String addRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add";
    }

    @RequestMapping(value = "recipe/add", method = RequestMethod.POST)
    public String addRecipe(Model model, @ModelAttribute ("recipe") Recipe recipe) {
        recipeService.add(recipe);
        return "redirect:/recipe/list";
    }

    @RequestMapping(value = "recipe/update", method = RequestMethod.GET)
    public String updateRecipeForm(@ModelAttribute ("id") String id, Model model) {
        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));
        return "update";
    }

    @RequestMapping(value = "recipe/update", method = RequestMethod.POST)
    public String updateRecipe(Model model, @ModelAttribute ("recipe") Recipe recipe) {
        recipeService.update(recipe);
        return "redirect:/recipe/list";
    }

    @RequestMapping(value = "recipe/delete", method = RequestMethod.GET)
    public String deleteRecipe(@ModelAttribute ("id") String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipe/list";
    }
}
