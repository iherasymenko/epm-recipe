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
    public RecipeController(RecipeService service) {
        this.recipeService = Objects.requireNonNull(service, "recipeService");
    }
    @GetMapping("recipes")
    public String findAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }
    @GetMapping("/oneRecipe")
    public String oneRecipe(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("recipeSearch", new Recipe());
        Recipe oneRecipe = null;
        if (id != null) {
            oneRecipe = recipeService.findRecipeById(id);
        }
        model.addAttribute("recipe", oneRecipe);
        return "oneRecipe";
    }
    @PostMapping("/oneRecipe")
    public String processSelectedRecipe(@ModelAttribute Recipe recipeSearch) {
        return "redirect:oneRecipe?id=" + recipeSearch.getId();
    }
    @GetMapping("createRecipe")
    public String createRecipePage(Model model) {
        model.addAttribute("createRecipe", new Recipe());
        return "createRecipe";
    }
    @PostMapping("createRecipe")
    public String createRecipe(@ModelAttribute Recipe recipe) {
        recipeService.createRecipe(recipe);
        return "redirect:info";
    }
    @GetMapping("updateRecipe")
    public String showUpdateRecipePage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("recipeUpdate", new Recipe());
        return "updateRecipe";
    }
    @PostMapping("updateRecipe")
    public String doUpdateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.updateRecipe(recipe);
        return "redirect:info";
    }
    @GetMapping("removeRecipe")
    public String deleteRecipePage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("recipeDelete", new Recipe());
        return "removeRecipe";
    }
    @PostMapping("removeRecipe")
    public String deleteRecipe(@ModelAttribute Recipe recipe) {
        recipeService.deleteRecipe(recipe);
        return "redirect:info";
    }
    @GetMapping("info")
    public String infoPage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "info";
    }
}
