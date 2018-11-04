package com.epm.recipe.converter.impl;

import com.epm.recipe.converter.RecipeConverter;
import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;

import java.util.ArrayList;
import java.util.List;

public class DefaultRecipeConverter implements RecipeConverter {
    @Override
    public Recipe convertToRecipe(CreateRecipeDto recipeDto) {
        return new Recipe(recipeDto.getTitle(), 0);
    }

    @Override
    public Recipe convertToRecipe(UpdateRecipeDto recipeDto) {
        return new Recipe(recipeDto.getTitle(), recipeDto.getId());
    }

    @Override
    public ViewRecipeDto convertToViewDto(Recipe recipe) {
        return new ViewRecipeDto(recipe.title, recipe.id);
    }

    @Override
    public ViewRecipeDto convertToViewDto(UpdateRecipeDto recipeDto) {
        return new ViewRecipeDto(recipeDto.getTitle(), recipeDto.getId());
    }

    @Override
    public List<ViewRecipeDto> convertToViewDtos(List<Recipe> recipes) {
        List<ViewRecipeDto> viewRecipeDtos = new ArrayList<>();
        for (Recipe recipe : recipes) {
            viewRecipeDtos.add(convertToViewDto(recipe));
        }
        return viewRecipeDtos;
    }
}
