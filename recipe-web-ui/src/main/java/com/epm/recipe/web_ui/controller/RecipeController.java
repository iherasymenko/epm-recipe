package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.exception.ApplicationException;
import com.epm.recipe.service.RecipeService;

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

    @GetMapping("/")
    public String recipeOfTheDay(Model model) {
        model.addAttribute("recipe", recipeService.recipeOfTheDay());
        return "recipe";
    }

    @GetMapping("recipes")
    public String showAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }

    @GetMapping("recipeById")
    public String showOneById(Model model, @RequestParam(required = false) Long id) {
        model.addAttribute("recipeSearch", new Recipe());
        Recipe recipeById = null;
        if (id != null) {
            try {
                recipeById = recipeService.findById(id);
            } catch (ApplicationException e) {
                return "redirect:error?exceptionMessage=" + e.getMessage();
            }
        }
        model.addAttribute("recipe", recipeById);
        return "recipeById";
    }

    @PostMapping("recipeById")
    public String processSelectedRecipe(@ModelAttribute Recipe recipeSearch) {
        return "redirect:recipeById?id=" + recipeSearch.getId();
    }

    @GetMapping("createRecipe")
    public String showCreateRecipePage(Model model) {
        model.addAttribute("createRecipe", new Recipe());
        return "createRecipe";
    }

    @PostMapping("createRecipe")
    public String doCreateRecipe(@ModelAttribute Recipe recipe) {
        try {
            recipeService.create(recipe);
            return "redirect:success";
        } catch (ApplicationException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }

    @GetMapping("updateRecipe")
    public String showUpdateRecipePage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("recipeUpdate", new Recipe());
        return "updateRecipe";
    }

    @PostMapping("updateRecipe")
    public String doUpdateRecipe(@ModelAttribute Recipe recipe) {
        try {
            recipeService.update(recipe);
            return "redirect:success";
        } catch (ApplicationException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }

    @GetMapping("deleteRecipe")
    public String showDeleteRecipePage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        model.addAttribute("recipeDelete", new Recipe());
        return "deleteRecipe";
    }

    @PostMapping("deleteRecipe")
    public String doDeleteRecipe(@ModelAttribute Recipe recipe) {
        try {
            recipeService.delete(recipe);
            return "redirect:success";
        } catch (ApplicationException e) {
            return "redirect:error?exceptionMessage=" + e.getMessage();
        }
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("success")
    public String showSuccessPage(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "success";
    }

    @GetMapping("error")
    public String showErrorPage(Model model, @RequestParam String exceptionMessage) {
        model.addAttribute("exception", exceptionMessage);
        return "errorPage";
    }

}
