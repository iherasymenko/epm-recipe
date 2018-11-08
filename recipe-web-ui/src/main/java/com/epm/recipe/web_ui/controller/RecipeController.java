package com.epm.recipe.web_ui.controller;

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

    @GetMapping("/")
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeService.readAll());
        return "recipe";
    }

    @PostMapping("/")
    public String createRecipe(@RequestParam(value="title") String title) {
        recipeService.createRecipe(title);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String updateRecipe(@PathVariable long id, @RequestParam(value="title") String title) {
        recipeService.updateRecipe(id, title);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/";
    }

}
