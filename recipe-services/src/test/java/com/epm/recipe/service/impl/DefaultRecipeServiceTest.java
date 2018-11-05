package com.epm.recipe.service.impl;

import com.epm.recipe.converter.RecipeConverter;
import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.exceptions.RecipeRepositoryException;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;
import com.epm.recipe.service.exceptions.NoRecipeWithSuchIdException;
import com.epm.recipe.service.exceptions.NoRecipesException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DefaultRecipeServiceTest {
    private RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
    private RecipeConverter recipeConverter = Mockito.mock(RecipeConverter.class);
    private RecipeService recipeService = new DefaultRecipeService(recipeRepository, recipeConverter);

    @Test
    public void gettingRecipeById() {
        //GIVEN
        ViewRecipeDto expectedRecipeDto = new ViewRecipeDto("title", 1);
        when(recipeRepository.findById(1)).thenReturn(new Recipe("title", 1));
        when(recipeConverter.convertToViewDto(any(Recipe.class))).thenReturn(expectedRecipeDto);
        //WHEN
        ViewRecipeDto actualRecipeDto = recipeService.getById(1);
        //THEN
        assertSame(expectedRecipeDto, actualRecipeDto);
    }

    @Test
    public void gettingUnexistingRecipeById() {
        //GIVEN
        when(recipeRepository.findById(1)).thenThrow(new RecipeRepositoryException("There is no recipe with id = 1"));
        //WHEN THEN
        assertThrows(NoRecipeWithSuchIdException.class,
                () -> {
                    recipeService.getById(1);
                },
                "There is no recipe with id = 1");
    }

    @Test
    public void gettingAllRecipes() {
        //GIVEN
        List<ViewRecipeDto> expectedRecipesDto = new ArrayList<>();
        expectedRecipesDto.add(new ViewRecipeDto("title", 1));
        expectedRecipesDto.add(new ViewRecipeDto("title2", 2));

        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new Recipe("title", 1));
        expectedRecipes.add(new Recipe("title2", 2));

        when(recipeRepository.findAll()).thenReturn(expectedRecipes);
        when(recipeConverter.convertToViewDtos(expectedRecipes)).thenReturn(expectedRecipesDto);
        //WHEN
        List<ViewRecipeDto> actualRecipesDto = recipeService.getAll();
        //THEN
        assertSame(expectedRecipesDto, actualRecipesDto);
    }

    @Test
    public void gettingAllRecipesWhenThereAreNo() {
        //GIVEN
        when(recipeRepository.findAll()).thenThrow(new RecipeRepositoryException("There is no recipes"));
        //WHEN THEN
        assertThrows(NoRecipesException.class,
                () -> {
                    recipeService.getAll();
                },
                "There is no recipes");
    }

    @Test
    public void creatingRecipe() {
        //GIVEN
        Recipe recipe = new Recipe("title", 0);
        CreateRecipeDto recipeDto = new CreateRecipeDto();
        recipeDto.setTitle("title");
        when(recipeConverter.convertToRecipe(recipeDto)).thenReturn(recipe);
        //WHEN
        recipeService.create(recipeDto);
        //THEN
        verify(recipeConverter, times(1)).convertToRecipe(recipeDto);
        verify(recipeRepository, times(1)).create(recipe);
    }

    @Test
    public void updatingRecipe() {
        //GIVEN
        Recipe recipe = new Recipe("title", 1);
        UpdateRecipeDto recipeDto = new UpdateRecipeDto();
        recipeDto.setTitle("title");
        recipeDto.setId(1);
        when(recipeConverter.convertToRecipe(recipeDto)).thenReturn(recipe);
        //WHEN
        recipeService.update(recipeDto);
        //THEN
        verify(recipeConverter, times(1)).convertToRecipe(recipeDto);
        verify(recipeRepository, times(1)).update(recipe);
    }

    @Test
    public void updatingUnexistingRecipe() {
        //GIVEN
        Recipe recipe = new Recipe("title", 1);
        UpdateRecipeDto recipeDto = new UpdateRecipeDto();
        recipeDto.setTitle("title");
        recipeDto.setId(1);
        when(recipeConverter.convertToRecipe(recipeDto)).thenReturn(recipe);
        doThrow(new RecipeRepositoryException("There is no recipe with id = 1"))
                .when(recipeRepository)
                .update(recipe);
        //WHEN THEN
        assertThrows(NoRecipeWithSuchIdException.class,
                () -> {
                    recipeService.update(recipeDto);
                },
                "There is no recipe with id = 1");
    }

    @Test
    public void deletingRecipe() {
        //GIVEN
        //WHEN
        recipeService.delete(1);
        //THEN
        verify(recipeRepository, times(1)).delete(1);
    }

    @Test
    public void deletingUnexistingRecipe() {
        //GIVEN
        doThrow(new RecipeRepositoryException("There is no recipe with id = 1"))
                .when(recipeRepository)
                .delete(1);
        //WHEN THEN
        assertThrows(NoRecipeWithSuchIdException.class,
                () -> {
                    recipeService.delete(1);
                },
                "There is no recipe with id = 1");
    }
}
