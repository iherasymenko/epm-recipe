package com.epm.recipe.service.impl;

import com.epm.recipe.domain.Recipe;
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
            throw new IllegalStateException("No recipes at all");
        }
        return all.get(0);
    }

    @Override
    public Recipe getById(long id) {
        return recipeRepository.getById(id);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public void add(Recipe recipe) {
        recipeRepository.add(recipe);
    }

    @Override
    public void update(Recipe recipe) {
        recipeRepository.update(recipe);
    }

    @Override
    public void deleteById(long id) {
        recipeRepository.deleteById(id);
    }




}
