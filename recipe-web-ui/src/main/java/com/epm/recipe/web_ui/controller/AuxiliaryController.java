package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuxiliaryController {

    private final RecipeService recipeService;

    public AuxiliaryController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("/index")
    public ModelAndView showIndexPage() {
        return new ModelAndView("index");
    }

    @GetMapping("success")
    public ModelAndView showSuccessPage() {
        ModelAndView modelAndView = new ModelAndView("success");
        List<Recipe> recipes = recipeService.findAll();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

    @GetMapping("error")
    public ModelAndView showErrorPage(@RequestParam String exceptionMessage) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exceptionMessage);
        return modelAndView;
    }
}