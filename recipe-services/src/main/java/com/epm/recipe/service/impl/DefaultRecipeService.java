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
    public Recipe byId(long id) {
        return recipeRepository.find(id);
    }

    @Override
    public List<Recipe> all() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe add(String title) {
        Recipe recipe = new Recipe(0, title);
        return recipeRepository.create(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) {
        return recipeRepository.update(recipe);
    }

    @Override
    public boolean delete(long id) {
        return recipeRepository.delete(id);
    }

}
