package com.epm.recipe.web_ui.controller;

import com.epm.recipe.service.RecipeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("all")
    public String all(Model model) {
        model.addAttribute("recipes", recipeService.all());
        return "all";
    }

    @GetMapping("recipe/{id}")
    public String recipe(Model model, @PathVariable int id) {
        model.addAttribute("recipe", recipeService.byId(id));
        return "recipe";
    }

    @GetMapping("restaurant")
    public String insert(@RequestParam("restaurant") String restaurant, Model model) {
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("recipes", recipeService.getByRestaurant(restaurant));
        return "all";
    }

}
