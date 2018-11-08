package com.epm.recipe.web_ui.controller;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }


    @GetMapping("/")
    public String getAll(Model model) throws SQLException {
        model.addAttribute("recipeList", recipeService.allInDB());
        return "recipeList";
    }

   @PostMapping("/post")
   public String postRecipe(@ModelAttribute(name = "recipe") Recipe recipe) {
       recipeService.Create(recipe);
       return "redirect:/";
   }

    @PostMapping("/put")
    public String putRecipe(@RequestParam("id") String id, @RequestParam("title") String titile) {
        recipeService.Update(new Recipe(titile, Long.parseLong(id)),Long.parseLong(id));
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String deleteRecipe(@RequestParam("id") String id) {
        recipeService.Delete(Long.parseLong(id));
        return "redirect:/";
    }
}
