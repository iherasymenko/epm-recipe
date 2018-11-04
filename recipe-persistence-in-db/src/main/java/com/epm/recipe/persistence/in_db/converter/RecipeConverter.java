package com.epm.recipe.persistence.in_db.converter;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.in_db.dto.RecipeDto;

import java.util.LinkedList;
import java.util.List;

public class RecipeConverter implements Converter <Recipe, RecipeDto> {
    @Override
    public RecipeDto asDto(Recipe recipe) {
        return new RecipeDto(recipe.getTitle(), recipe.getId());
    }

    @Override
    public Recipe asObj(RecipeDto recipeDto) {
        return new Recipe(recipeDto.getTitle(), recipeDto.getId());
    }

    @Override
    public List<Recipe> asObj(List<RecipeDto> recipeDtos) {
        List<Recipe> recipeList = new LinkedList<>();
        recipeDtos.forEach(recipeDto -> {recipeList.add(asObj(recipeDto));});
        return recipeList;
    }
}
