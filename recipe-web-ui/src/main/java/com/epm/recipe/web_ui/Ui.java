package com.epm.recipe.web_ui;

import com.epm.recipe.service.RecipeService;

import java.net.URL;
import java.util.Objects;

public class Ui {

    private final RecipeService recipeService;

    public Ui(RecipeService recipeService) {
        this.recipeService = Objects.requireNonNull(recipeService, "recipeService");
    }

    public void showRecipeOfTheDay() {
        System.out.println(recipeService.recipeOfTheDay());
    }

}
