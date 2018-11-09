package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.RecipeTransformer;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.ShowRecipe;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class DefaultRecipeServiceTest {

    private RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
    private RecipeTransformer recipeTransformer = Mockito.mock(RecipeTransformer.class);
    private RecipeService recipeService = new DefaultRecipeService(recipeRepository, recipeTransformer);

    @Test
    public void getRecipeById() {
        //GIVEN
        ShowRecipe expected = new ShowRecipe("first", 1);
        //WHEN
        when(recipeRepository.findById(1)).thenReturn(new Recipe("first", 1));
        when(recipeTransformer.transformToView(any(Recipe.class))).thenReturn(expected);
        ShowRecipe actual = recipeService.getById(1);
        //THEN
        assertSame(expected, actual);
    }

    @Test
    public void getAllRecipes() {
        //GIVEN
        List<ShowRecipe> expectedShowRecipes = new ArrayList<>();
        expectedShowRecipes.add(new ShowRecipe("first", 1));
        expectedShowRecipes.add(new ShowRecipe("second", 2));

        List<Recipe> expectedRecipes = new ArrayList<>();
        expectedRecipes.add(new Recipe("first", 1));
        expectedRecipes.add(new Recipe("second", 2));
        //WHEN
        when(recipeRepository.findAll()).thenReturn(expectedRecipes);
        when(recipeTransformer.transformToViews(expectedRecipes)).thenReturn(expectedShowRecipes);
        List<ShowRecipe> actualShowRecipes = recipeService.getAll();
        //THEN
        assertSame(expectedShowRecipes, actualShowRecipes);
    }

    @Test
    public void createRecipe() {
        //GIVEN
        CreateRecipe createRecipe = new CreateRecipe();
        createRecipe.setTitle("zero");
        Recipe recipe = new Recipe("zero", 0);

        //WHEN
        when(recipeTransformer.transformToRecipe(createRecipe)).thenReturn(recipe);
        recipeService.create(createRecipe);
        //THEN
        verify(recipeTransformer, times(1)).transformToRecipe(createRecipe);
        verify(recipeRepository, times(1)).create(recipe);
    }
}
