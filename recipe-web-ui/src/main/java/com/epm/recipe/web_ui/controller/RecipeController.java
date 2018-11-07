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

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping(value = "/")
    public String recipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "form";
    }

    @GetMapping("/recipeOfTheDay")
    public String recipeOfTheDay(Model model) {
        model.addAttribute("recipe", recipeService.recipeOfTheDay());
        return "recipe";
    }

    @GetMapping(value = "/getAllRecipes")
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "allRecipes";
    }

    @RequestMapping(value = "/recipeById/{id}", method = RequestMethod.GET)
    public String recipeById(@PathVariable long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe";
    }

    @GetMapping(value = "/deleteRecipe")
    public String delete(@ModelAttribute Recipe recipe, Model model) {
        if (recipeService.delete(recipe.getId())) {
            model.addAttribute("result", "Recipe with id:" + recipe.getId() + " successfully removed");
        } else {
            model.addAttribute("result", "Recipe with id:" + recipe.getId() + " has not been deleted. Make sure the data is correct");
        }
        return "recipe";
    }

    @PostMapping(value = "/createNewRecipe")
    public String create(@ModelAttribute Recipe recipe, Model model) {
        recipe.setId(1L);
        if (recipeService.create(recipe)) {
            model.addAttribute("result", "Recipe " + recipe.getTitle() + "  successfully added");
        } else {
            model.addAttribute("result", recipe.getTitle() + " has not been created. Make sure the data is correct");
        }
        return "recipe";
    }

    @PostMapping(value = "/updateRecipe")
    public String update(@ModelAttribute Recipe recipe, Model model) {
        if (recipeService.update(recipe)) {
            model.addAttribute("result", "Recipe " + recipe.getTitle() + "  successfully updated");
        } else {
            model.addAttribute("result", "Recipe " + recipe.getTitle() + "  has not been created. Make sure the data is correct");
        }

        return "recipe";
    }

}
