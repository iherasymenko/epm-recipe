package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.ShowRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultRecipeTransformerTest {

    private DefaultRecipeTransformer defaultRecipeTransformer = new DefaultRecipeTransformer();

    @Test
    public void transformToRecipeWithFieldCreateRecipe() {
        //GIVEN
        Recipe expected = new Recipe("Fernando", 0);
        //WHEN
        CreateRecipe createRecipe = new CreateRecipe();
        createRecipe.setTitle("Fernando");
        Recipe actual = defaultRecipeTransformer.transformToRecipe(createRecipe);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void transformToRecipeWithFieldUpdateRecipe() {
        //GIVEN
        Recipe expected = new Recipe("Antonio", 15);
        //WHEN
        UpdateRecipe updateRecipe = new UpdateRecipe();
        updateRecipe.setTitle("Antonio");
        updateRecipe.setId(15);
        Recipe actual = defaultRecipeTransformer.transformToRecipe(updateRecipe);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void transformRecipeToViewWithFieldRecipe() {
        //GIVEN
        Recipe recipe = new Recipe("Ahmed", 30);
        ShowRecipe expectedViewDto = new ShowRecipe("Ahmed", 30);
        //WHEN
        ShowRecipe actualViewDto = defaultRecipeTransformer.transformToView(recipe);
        //THEN
        assertEquals(expectedViewDto, actualViewDto);
    }
}
