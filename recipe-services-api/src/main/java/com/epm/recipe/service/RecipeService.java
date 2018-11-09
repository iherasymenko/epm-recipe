package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;

import java.util.ArrayList;

public interface RecipeService {

    ArrayList<Recipe> getAllRecipes();

    Recipe addRecipe(Integer id, String title);

    Recipe deleteRecipe(Integer id);

    Recipe updateRecipe(Integer id, String title);
}
