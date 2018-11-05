package com.epm.recipe.service.impl;

import com.epm.recipe.converter.RecipeConverter;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.exceptions.RecipeRepositoryException;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.service.dto.CreateRecipeDto;
import com.epm.recipe.service.dto.UpdateRecipeDto;
import com.epm.recipe.service.dto.ViewRecipeDto;
import com.epm.recipe.service.exceptions.NoRecipeWithSuchIdException;
import com.epm.recipe.service.exceptions.NoRecipesException;

import java.util.List;
import java.util.Objects;

public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConverter recipeConverter;

    public DefaultRecipeService(RecipeRepository recipeRepository, RecipeConverter recipeConverter) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
        this.recipeConverter = Objects.requireNonNull(recipeConverter, "recipeConverter");
    }

    @Override
    public ViewRecipeDto recipeOfTheDay() {
        List<ViewRecipeDto> all = getAll();
        return all.get(0);
    }

    @Override
    public ViewRecipeDto getById(long id) {
        try {
            return recipeConverter.convertToViewDto(recipeRepository.findById(id));
        } catch (RecipeRepositoryException e) {
            throw new NoRecipeWithSuchIdException(e);
        }
    }

    @Override
    public List<ViewRecipeDto> getAll() {
        try {
            return recipeConverter.convertToViewDtos(recipeRepository.findAll());
        } catch (RecipeRepositoryException e) {
            throw new NoRecipesException(e);
        }
    }

    @Override
    public void create(CreateRecipeDto createDto) {
        recipeRepository.create(recipeConverter.convertToRecipe(createDto));
    }

    @Override
    public ViewRecipeDto update(UpdateRecipeDto recipeDto) {
        try {
            recipeRepository.update(recipeConverter.convertToRecipe(recipeDto));
        } catch (RecipeRepositoryException e) {
            throw new NoRecipeWithSuchIdException(e);
        }
        return recipeConverter.convertToViewDto(recipeDto);
    }

    @Override
    public void delete(long id) {
        try {
            recipeRepository.delete(id);
        } catch (RecipeRepositoryException e) {
            throw new NoRecipeWithSuchIdException(e);
        }
    }

}
