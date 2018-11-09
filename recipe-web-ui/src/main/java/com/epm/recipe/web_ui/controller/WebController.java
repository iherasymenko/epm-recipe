package com.epm.recipe.web_ui.controller;


import com.epm.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class WebController {

    private final RecipeService recipeService;

    public WebController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    @GetMapping("/")
    public String recipeOfTheDay() { return "service"; }

    @RequestMapping(value = "addRecipe", method = RequestMethod.POST)
    public String addRecipe(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "title") String title) {

        recipeService.addRecipe(id, title);

        return "service";
    }

    @RequestMapping(value = "updateRecipe", method = RequestMethod.POST)
    public String updateRecipe(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "title") String title) {

        recipeService.updateRecipe(id, title);

        return "service";
    }

    @RequestMapping(value = "deleteRecipe", method = RequestMethod.POST)
    public String deleteRecipe(@RequestParam(value = "id") Integer id) {

        recipeService.deleteRecipe(id);

        return "service";
    }

    @RequestMapping(value = "showRecipes", method = RequestMethod.POST)
    public String showRecipes(Model model) {

        ArrayList recipes = new ArrayList();
        recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "showRecipes";
    }

    @RequestMapping(value = "servicePage", method = RequestMethod.POST)
    public String serviceButton() {
        return "service";
    }

}
