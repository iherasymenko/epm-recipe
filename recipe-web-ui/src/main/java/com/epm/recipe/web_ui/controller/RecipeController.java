package com.epm.recipe.web_ui.controller;

import com.epm.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "all";
    }

    @PostMapping("/add")
    public String add(@RequestParam("id") int id, @RequestParam("title") String title, Model model) {
        if (recipeService.add(id, title)) {
            model.addAttribute("recipe", recipeService.byId(id).orElse(null));
        }
        model.addAttribute("operation", "add");
        return "result";
    }

    @PostMapping("/delete")
    public String removeById(@RequestParam("id") int id, Model model) {
        model.addAttribute("recipe", recipeService.removeById(id));
        model.addAttribute("operation", "delete");
        return "result";
    }

    @PostMapping("/update")
    public String updateById(@RequestParam("id") int id, @RequestParam("title") String title, Model model) {
        model.addAttribute("recipe", recipeService.updateValueById(id, title));
        model.addAttribute("operation", "update");
        return "result";
    }
}
