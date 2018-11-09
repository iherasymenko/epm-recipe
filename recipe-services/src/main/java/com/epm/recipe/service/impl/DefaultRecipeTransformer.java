package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeTransformer;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;

import java.util.ArrayList;
import java.util.List;

public class DefaultRecipeTransformer implements RecipeTransformer {
    @Override
    public Recipe transformToRecipe(CreateRecipe recipeCreate) {
        return new Recipe(recipeCreate.getTitle(), 0);
    }

    @Override
    public Recipe transformToRecipe(UpdateRecipe recipeUpdate) {
        return new Recipe(recipeUpdate.getTitle(), recipeUpdate.getId());
    }

    @Override
    public ShowRecipe transformToView(Recipe recipe) {
        return new ShowRecipe(recipe.title, recipe.id);
    }

    @Override
    public ShowRecipe transformToView(UpdateRecipe recipeUpdate) {
        return new ShowRecipe(recipeUpdate.getTitle(), recipeUpdate.getId());
    }

    @Override
    public List<ShowRecipe> transformToViews(List<Recipe> recipes) {
        List<ShowRecipe> showRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            showRecipes.add(transformToView(recipe));
        }
        return showRecipes;
    }
}
