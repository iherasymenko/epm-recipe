package com.epm.recipe.service;

import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;

import java.util.List;

public interface RecipeService {
    ViewRecipeDto recipeOfTheDay();
    ViewRecipeDto getById(long id);
    List<ViewRecipeDto> getAll();
    void create(CreateRecipeDto recipe);
    ViewRecipeDto update(UpdateRecipeDto recipe);
    void delete(long id);

}
