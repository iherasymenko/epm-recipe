package com.epm.recipe.converter.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultRecipeConverterTest {
    private DefaultRecipeConverter converter = new DefaultRecipeConverter();

    @Test
    public void convertCreateDtoToRecipe() {
        //GIVEN
        CreateRecipeDto recipeDto = new CreateRecipeDto();
        recipeDto.setTitle("title");
        Recipe expectedRecipe = new Recipe("title", 0);
        //WHEN
        Recipe actualRecipe = converter.convertToRecipe(recipeDto);
        //THEN
        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test
    public void convertUpdateDtoToRecipe() {
        //GIVEN
        UpdateRecipeDto recipeDto = new UpdateRecipeDto();
        recipeDto.setTitle("title");
        recipeDto.setId(10);
        Recipe expectedRecipe = new Recipe("title", 10);
        //WHEN
        Recipe actualRecipe = converter.convertToRecipe(recipeDto);
        //THEN
        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test
    public void convertUpdateDtoToViewDto() {
        //GIVEN
        UpdateRecipeDto recipeDto = new UpdateRecipeDto();
        recipeDto.setTitle("title");
        recipeDto.setId(10);
        ViewRecipeDto expectedViewDto = new ViewRecipeDto("title", 10);
        //WHEN
        ViewRecipeDto actualViewDto = converter.convertToViewDto(recipeDto);
        //THEN
        assertEquals(expectedViewDto, actualViewDto);
    }

    @Test
    public void convertRecipeToViewDto() {
        //GIVEN
        Recipe recipe = new Recipe("title", 10);
        ViewRecipeDto expectedViewDto = new ViewRecipeDto("title", 10);
        //WHEN
        ViewRecipeDto actualViewDto = converter.convertToViewDto(recipe);
        //THEN
        assertEquals(expectedViewDto, actualViewDto);
    }

    @Test
    public void convertMultipleRecipesToViewDtos() {
        //GIVEN
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("title", 10));
        recipes.add(new Recipe("title2", 20));
        List<ViewRecipeDto> expectedViewDtos = new ArrayList<>();
        expectedViewDtos.add(new ViewRecipeDto("title", 10));
        expectedViewDtos.add(new ViewRecipeDto("title2", 20));
        //WHEN
        List<ViewRecipeDto> actualViewDtos = converter.convertToViewDtos(recipes);
        //THEN
        assertEquals(expectedViewDtos, actualViewDtos);
    }


}
