package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class DefaultRecipeServiceTest {

    private static RecipeService recipeService = new DefaultRecipeService();

    @BeforeEach
    void init() {
        recipeService.addRecipe(1, "Test1");
        recipeService.addRecipe(2, "Test2");
    }

    @AfterEach
    void destroy() {
        recipeService.deleteRecipe(1);
        recipeService.deleteRecipe(2);
    }

    @Test
    void getAllRecipes() {
        //GIVEN
        ArrayList<Recipe> expected = new ArrayList<>();
        //WHEN
        expected.add(new Recipe(1, "Test1"));
        expected.add(new Recipe(2, "Test2"));
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();
        //THEN
        Assertions.assertEquals(expected, allRecipes);
    }

    @Test
    void addRecipe() {
        //GIVEN
        Recipe expectedRecipe = new Recipe(3, "AddedRecipe");
        ArrayList<Recipe> expectedListOfRecipes = new ArrayList<>();
        //WHEN
        expectedListOfRecipes.add(new Recipe(1, "Test1"));
        expectedListOfRecipes.add(new Recipe(2, "Test2"));
        expectedListOfRecipes.add(new Recipe(3, "AddedRecipe"));
        Recipe addedRecipe = recipeService.addRecipe(3, "AddedRecipe");
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();
        recipeService.deleteRecipe(3);
        //THEN
        Assertions.assertEquals(expectedRecipe, addedRecipe);
        Assertions.assertEquals(expectedListOfRecipes, allRecipes);
    }

    @Test
    void deleteRecipe() {
        //GIVEN
        Recipe expectedRecipe = new Recipe(2, "Test2");
        ArrayList<Recipe> expectedListOfRecipes = new ArrayList<>();
        //WHEN
        expectedListOfRecipes.add(new Recipe(1, "Test1"));
        Recipe deletedRecipe = recipeService.deleteRecipe(2);
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();
        //THEN
        Assertions.assertEquals(expectedRecipe, deletedRecipe);
        Assertions.assertEquals(expectedListOfRecipes, allRecipes);
    }

    @Test
    void updateRecipe() {
        //GIVEN
        Recipe expectedRecipe = new Recipe(2, "Updated");
        ArrayList<Recipe> expectedListOfRecipes = new ArrayList<>();
        //WHEN
        expectedListOfRecipes.add(new Recipe(1, "Test1"));
        expectedListOfRecipes.add(new Recipe(2, "Updated"));
        Recipe updatedRecipe = recipeService.updateRecipe(2, "Updated");
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipes();
        //THEN
        Assertions.assertEquals(expectedRecipe, updatedRecipe);
        Assertions.assertEquals(expectedListOfRecipes, allRecipes);
    }
}