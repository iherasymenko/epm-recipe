package com.epm.recipe.service;

import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;

import java.util.List;

public interface RecipeService {

    List<ShowRecipe> getAll();
    ShowRecipe getById(long id);
    ShowRecipe recipeOfTheDay();
    void create(CreateRecipe recipe);
    void delete(long id);
    ShowRecipe update(UpdateRecipe recipe);


}

