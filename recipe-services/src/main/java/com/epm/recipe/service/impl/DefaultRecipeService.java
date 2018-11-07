package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.exception.ApplicationException;
import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.service.RecipeService;

import java.util.List;
import java.util.Objects;

public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;

    public DefaultRecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = Objects.requireNonNull(recipeRepository, "recipeRepository");
    }

    @Override
    public Recipe recipeOfTheDay() {
        List<Recipe> all = recipeRepository.findAll();
        if (all.isEmpty()) {
            throw new ApplicationException("Empty list");
        }
        return all.get(0);
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(long id) {
        return recipeRepository.getById(id);
    }

    @Override
    public void create(Recipe recipe) {
        recipeRepository.insert(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe.getId());
    }
}
