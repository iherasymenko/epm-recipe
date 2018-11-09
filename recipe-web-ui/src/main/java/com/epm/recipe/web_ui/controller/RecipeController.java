package com.epm.recipe.web_ui.controller;

import com.epm.recipe.service.RecipeService;

import com.epm.recipe.persistence.in_memory.exception.RecipeNotFoundException;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("/recipes/byId")
    public String getRecipeByIdPage() {
        return "recipeById";
    }

    @GetMapping(value = "/recipes/byId", params = "id")
    public String getRecipeById(long id, Model model) {
        try {
            model.addAttribute("recipe", recipeService.getById(id));
            return "showRecipe";
        } catch (RecipeNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "recipeById";
        }
    }

    @GetMapping(value = {"/recipes", "/"})
    public String getAllRecipes(Model model) {
        try {
            model.addAttribute("recipes", recipeService.getAll());
        } catch (RecipeNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
    }

    @GetMapping("/recipes/create")
    public String getCreateRecipePage(Model model) {
        model.addAttribute("createRecipe", new CreateRecipe());
        return "createRecipe";
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute CreateRecipe createRecipe) {
        recipeService.create(createRecipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/delete")
    public String getDeleteRecipePage() {
        return "deleteRecipe";
    }

    @PostMapping("/recipes/delete")
    public String deleteRecipePage(@RequestParam long id, Model model) {
        try {
            recipeService.delete(id);
            return "redirect:/recipes";
        } catch (RecipeNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "deleteRecipe";
        }
    }

    @GetMapping("/recipes/update")
    public String getUpdateRecipePage(Model model) {
        model.addAttribute("updateRecipe", new UpdateRecipe());
        return "updateRecipe";
    }

    @PostMapping("/recipes/update")
    public String updateRecipe(@ModelAttribute UpdateRecipe updateRecipe, Model model) {
        try {
            ShowRecipe showRecipe = recipeService.update(updateRecipe);
            model.addAttribute("recipe", showRecipe);
            return "showRecipe";
        } catch (RecipeNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "updateRecipe";
        }
    }
}
