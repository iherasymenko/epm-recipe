package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("recipe")
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

    @RequestMapping("/list")
    public String showAllRecipies(Model model) {
        model.addAttribute("recipies", recipeService.getAll());
        return "list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRecipe(Model model, @ModelAttribute ("recipe") Recipe recipe) {
        recipeService.add(recipe);
        return "redirect:/recipe/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateRecipeForm(@ModelAttribute ("id") String id, Model model) {
        model.addAttribute("recipe", recipeService.getById(Long.valueOf(id)));
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRecipe(Model model, @ModelAttribute ("recipe") Recipe recipe) {
        recipeService.update(recipe);
        return "redirect:/recipe/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteRecipe(Model model, @ModelAttribute ("id") String id) {
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipe/list";
    }
}
