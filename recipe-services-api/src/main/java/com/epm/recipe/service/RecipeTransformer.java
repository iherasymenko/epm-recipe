package com.epm.recipe.service;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;

import java.util.List;

public interface RecipeTransformer {

    List<ShowRecipe> transformToViews(List<Recipe> recipes);
    Recipe transformToRecipe(CreateRecipe recipeCreate);
    Recipe transformToRecipe(UpdateRecipe recipeUpdate);
    ShowRecipe transformToView(UpdateRecipe recipe);
    ShowRecipe transformToView(Recipe recipe);

}