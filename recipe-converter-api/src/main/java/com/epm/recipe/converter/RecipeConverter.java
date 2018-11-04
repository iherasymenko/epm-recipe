package com.epm.recipe.converter;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;

import java.util.List;

public interface RecipeConverter {
     Recipe convertToRecipe(CreateRecipeDto recipeDto);
     Recipe convertToRecipe(UpdateRecipeDto recipeDto);
     ViewRecipeDto convertToViewDto(Recipe recipe);
     ViewRecipeDto convertToViewDto(UpdateRecipeDto recipe);
     List<ViewRecipeDto> convertToViewDtos(List<Recipe> recipes);
}
