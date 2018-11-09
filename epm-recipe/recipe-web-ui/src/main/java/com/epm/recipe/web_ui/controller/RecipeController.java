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

    @GetMapping("/list")
    public String recipesList(Model model) {
        if(recipeService.findAll().isEmpty()){
            model.addAttribute("recipe", new Recipe("", -99));
            return "recipe_form_create";
        }else {
            model.addAttribute("recipes", recipeService.findAll());
            return "recipe_list";
        }
    }

    @GetMapping("/go_create")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe("", -99));
        return "recipe_form_create";
    }

    @PostMapping("/create")
    public RedirectView createRecipe(@ModelAttribute Recipe recipe) {
        recipeService.create(recipe);
        return new RedirectView("/list");
    }


    @GetMapping("/go_update/{id}")
    public String updateRecipeForm(Model model, @PathVariable String id) {
        model.addAttribute("recipe", recipeService.findById(Long.parseLong(id)).get());
        return "recipe_form_update";
    }

    @PostMapping("/update")
    public RedirectView updateRecipe(@ModelAttribute Recipe recipe) {
        recipeService.update(recipe);
        return new RedirectView("/list");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteRecipe(@PathVariable String id) {
        recipeService.delete(Long.parseLong(id));
        return new RedirectView("/list");
    }
}
