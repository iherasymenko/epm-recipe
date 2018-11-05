package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("recipes")
    public ModelAndView showAllRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipes");
        List<Recipe> recipes = recipeService.findAll();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    @GetMapping("recipeById")
    public ModelAndView showOneById(@RequestParam(required = false) Long id) {
        ModelAndView modelAndView = new ModelAndView("recipeById");
        modelAndView.addObject("recipeSearch", new Recipe(0L, ""));
        Recipe recipeById = (id != null) ? recipeService.findOneById(id) : null;
        modelAndView.addObject("recipe", recipeById);
        return modelAndView;
    }

    @PostMapping("recipeById")
    public String processSelectedRecipe(@ModelAttribute Recipe recipeSearch) {
        return "redirect:recipeById?id=" + recipeSearch.getId();
    }

    @GetMapping("createRecipe")
    public ModelAndView showCreateRecipePage() {
        ModelAndView modelAndView = new ModelAndView("createRecipe");
        modelAndView.addObject("createRecipe", new Recipe(""));
        return modelAndView;
    }

    @PostMapping("createRecipe")
    public String doCreateRecipe(@ModelAttribute Recipe newRecipe) {
        try {
            recipeService.create(newRecipe);
            return "redirect:success";
        } catch (IllegalArgumentException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }

    @GetMapping("updateRecipe")
    public ModelAndView showUpdateRecipePage() {
        ModelAndView modelAndView = new ModelAndView("updateRecipe");
        List<Recipe> recipes = recipeService.findAll();
        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("recipeUpdate", new Recipe(0L, ""));
        return modelAndView;
    }

    @PostMapping("updateRecipe")
    public String doUpdateRecipe(@ModelAttribute Recipe newRecipe) {
        try {
            recipeService.update(newRecipe);
            return "redirect:success";
        } catch (IllegalArgumentException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }

    @GetMapping("deleteRecipe")
    public ModelAndView showDeleteRecipePage() {
        ModelAndView modelAndView = new ModelAndView("deleteRecipe");
        List<Recipe> recipes = recipeService.findAll();
        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("recipeDelete", new Recipe(0L, ""));
        return modelAndView;
    }

    @PostMapping("deleteRecipe")
    public String doDeleteRecipe(@ModelAttribute Recipe recipeSearch) {
        try {
            recipeService.delete(recipeSearch);
            return "redirect:success";
        } catch (IllegalArgumentException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }
}
