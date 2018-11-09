package com.epm.recipe.service.impl;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.in_memory.exception.RecipeInMemoryException;
import com.epm.recipe.service.RecipeTransformer;
import com.epm.recipe.service.RecipeService;
import com.epm.recipe.persistence.in_memory.exception.RecipeNotFoundException;
import com.epm.recipe.service.model.CreateRecipe;
import com.epm.recipe.service.model.UpdateRecipe;
import com.epm.recipe.service.model.ShowRecipe;

import java.util.List;
import java.util.Objects;

public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeTransformer recipeTransformer;

    public DefaultRecipeService(RecipeRepository recipeRepository, RecipeTransformer recipeTransformer) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
        this.recipeTransformer = Objects.requireNonNull(recipeTransformer, "recipeTransformer");
    }

    @Override
    public List<ShowRecipe> getAll() {
        try {
            return recipeTransformer.transformToViews(recipeRepository.findAll());
        } catch (RecipeInMemoryException e) { throw new RecipeNotFoundException(e.getMessage(), e); }
    }

    @Override
    public ShowRecipe getById(long id) {
        try {
            return recipeTransformer.transformToView(recipeRepository.findById(id));
        } catch (RecipeInMemoryException e) { throw new RecipeNotFoundException(e.getMessage(), e); }
    }

    @Override
    public ShowRecipe recipeOfTheDay() {
        List<ShowRecipe> all = getAll();
        return all.get(0);
    }

    @Override
    public void create(CreateRecipe createDto) {
        recipeRepository.create(recipeTransformer.transformToRecipe(createDto));
    }

    @Override
    public void delete(long id) {
        try {
            recipeRepository.delete(id);
        } catch (RecipeInMemoryException e) { throw new RecipeNotFoundException(e.getMessage(), e); }
    }

    @Override
    public ShowRecipe update(UpdateRecipe recipeDto) {
        try {
            recipeRepository.update(recipeTransformer.transformToRecipe(recipeDto));
        } catch (RecipeInMemoryException e) { throw new RecipeNotFoundException(e.getMessage(), e); }
        return recipeTransformer.transformToView(recipeDto);
    }
}

