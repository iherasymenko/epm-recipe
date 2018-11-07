package com.epm.recipe.web_ui.controller;

import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;
import com.epm.recipe.service.exceptions.NoRecipeWithSuchIdException;
import com.epm.recipe.service.exceptions.NoRecipesException;
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

    @GetMapping(value = {"/recipes", "/"})
    public String getAllRecipes(Model model) {
        try {
            model.addAttribute("recipes", recipeService.getAll());
        } catch (NoRecipesException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "index";
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
        } catch (NoRecipeWithSuchIdException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "recipeById";
        }
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
        } catch (NoRecipeWithSuchIdException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "deleteRecipe";
        }
    }

    @GetMapping("/recipes/create")
    public String getCreateRecipePage(Model model) {
        model.addAttribute("createDto", new CreateRecipeDto());
        return "createRecipe";
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute CreateRecipeDto createDto) {
        recipeService.create(createDto);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/update")
    public String getUpdateRecipePage(Model model) {
        model.addAttribute("updateDto", new UpdateRecipeDto());
        return "updateRecipe";
    }

    @PostMapping("/recipes/update")
    public String updateRecipe(@ModelAttribute UpdateRecipeDto updateDto, Model model) {
        try {
            ViewRecipeDto recipe = recipeService.update(updateDto);
            model.addAttribute("recipe", recipe);
            return "showRecipe";
        } catch (NoRecipeWithSuchIdException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "updateRecipe";
        }
    }

}
