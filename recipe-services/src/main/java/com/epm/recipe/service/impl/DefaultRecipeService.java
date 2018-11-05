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
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findOneById(Long id) throws IllegalArgumentException {
        try {
            return recipeRepository.findOneById(id);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("There are no recipe with such ID!");
        }
    }

    @Override
    public void create(Recipe recipe) {
        if (isRecipeNull(recipe)) {
            recipeRepository.create(recipe);
        }
    }

    @Override
    public Recipe update(Recipe newRecipe) throws IllegalArgumentException {
        Recipe oldRecipe = new Recipe();
        if (isRecipeNull(newRecipe)) {
            oldRecipe = findOneById(newRecipe.getId());
            recipeRepository.update(newRecipe);
        }
        return oldRecipe;
    }

    @Override
    public Recipe delete(Recipe recipe) throws IllegalArgumentException {
        Recipe deletedRecipe = new Recipe();
        if (isRecipeNull(recipe)) {
            deletedRecipe = findOneById(recipe.getId());
            recipeRepository.delete(recipe);
        }
        return deletedRecipe;
    }


    private boolean isRecipeNull(Recipe recipe) {
        return recipe != null;
    }
}